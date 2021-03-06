package chatroom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 * 利用ServerSocket实现的多人聊天
 * @author 风潇潇
 *
 */
public class Server {
	//所有封装后的客户端通道的集合
	List<MyTunnel> chatRoom = new ArrayList<MyTunnel>();
	public static void main(String[] args) throws IOException {
		new Server().start();
	}
	public void start() throws IOException{
		//以8888端口创建服务端
		ServerSocket sever = new ServerSocket(8888);
		//依据接收到的请求，为每个client创建通道并开启单独县城
		while(true){
			Socket client = sever.accept();
			MyTunnel mt = new MyTunnel(client);
			chatRoom.add(mt);
			new Thread(mt).start();
			
		}
	}
	/**
	 * 将客户端收发信息封装为内部类，便于使用
	 * @author 风潇潇
	 *
	 */
	private class MyTunnel implements Runnable{
		private DataOutputStream dos;
		private DataInputStream dis;
		boolean isRunning=true;
		private String name ;
		//初始化，创建输入流输出流同client建立联系
		public MyTunnel(Socket client) {
			try {
				dos = new DataOutputStream(client.getOutputStream());
				dis = new DataInputStream(client.getInputStream());
			} catch (IOException e) {
				isRunning=false;
				CloseUtil.Close.closeAll(dos,dis);
			}
		}
		//用户名设置
		public void setName(String name){
			this.name=name;
		}
		//从用户处收取消息
		private String getMsg(){
			String msg =null;
			try {
				msg=dis.readUTF();
			} catch (IOException e) {
				isRunning=false;
				CloseUtil.Close.closeAll(dos,dis);
				chatRoom.remove(this);
			}
			return msg;
		}
		//向用户发送消息
		public void send(String msg){
			if(msg==null){
				return;
			}
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				isRunning=false;
				CloseUtil.Close.closeAll(dos,dis);
				chatRoom.remove(this);
			}
		}
		//聊天室和私聊
		public void sendAll(){
			String msg = this.getMsg();
			if(msg!=null&&msg.startsWith("@")&&msg.contains("：")){
				String name = msg.substring(1, msg.indexOf('：'));
				String content = msg.substring(msg.indexOf('：')+1);
				for(MyTunnel mt:chatRoom){
					if(mt.name.equals(name)){
						mt.send(this.name+"的悄悄话："+content);
					}
					else{
						this.send(name+"用户不存在");
					}
				}
			}else{
				for(MyTunnel mt:chatRoom){
					if(mt==this){
						continue;
					}
					mt.send(this.name+":"+msg);
				}
			}
		}
		//初始化用户，接受用户端用户名，并发送欢迎消息
		public void iniClient(){
			String name = this.getMsg();
			this.setName(name);
			for(MyTunnel mt:chatRoom){
				if(mt==this){
					continue;
				}
				mt.send("欢迎"+name+"进入聊天室");
			}
		}
		@Override
		public void run() {
			iniClient();
			while(isRunning){
				sendAll();
			}
		}
		
	}

}
