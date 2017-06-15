package chatroom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 * ����ServerSocketʵ�ֵĶ�������
 * @author ������
 *
 */
public class Server {
	//���з�װ��Ŀͻ���ͨ���ļ���
	List<MyTunnel> chatRoom = new ArrayList<MyTunnel>();
	public static void main(String[] args) throws IOException {
		new Server().start();
	}
	public void start() throws IOException{
		//��8888�˿ڴ��������
		ServerSocket sever = new ServerSocket(8888);
		//���ݽ��յ�������Ϊÿ��client����ͨ�������������س�
		while(true){
			Socket client = sever.accept();
			MyTunnel mt = new MyTunnel(client);
			chatRoom.add(mt);
			new Thread(mt).start();
			
		}
	}
	/**
	 * ���ͻ����շ���Ϣ��װΪ�ڲ��࣬����ʹ��
	 * @author ������
	 *
	 */
	private class MyTunnel implements Runnable{
		private DataOutputStream dos;
		private DataInputStream dis;
		boolean isRunning=true;
		private String name ;
		//��ʼ�������������������ͬclient������ϵ
		public MyTunnel(Socket client) {
			try {
				dos = new DataOutputStream(client.getOutputStream());
				dis = new DataInputStream(client.getInputStream());
			} catch (IOException e) {
				isRunning=false;
				CloseUtil.Close.closeAll(dos,dis);
			}
		}
		//�û�������
		public void setName(String name){
			this.name=name;
		}
		//���û�����ȡ��Ϣ
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
		//���û�������Ϣ
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
		//�����Һ�˽��
		public void sendAll(){
			String msg = this.getMsg();
			if(msg!=null&&msg.startsWith("@")&&msg.contains("��")){
				String name = msg.substring(1, msg.indexOf('��'));
				String content = msg.substring(msg.indexOf('��')+1);
				for(MyTunnel mt:chatRoom){
					if(mt.name.equals(name)){
						mt.send(this.name+"�����Ļ���"+content);
					}
					else{
						this.send(name+"�û�������");
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
		//��ʼ���û��������û����û����������ͻ�ӭ��Ϣ
		public void iniClient(){
			String name = this.getMsg();
			this.setName(name);
			for(MyTunnel mt:chatRoom){
				if(mt==this){
					continue;
				}
				mt.send("��ӭ"+name+"����������");
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