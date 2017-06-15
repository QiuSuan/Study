package MySever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever1 {
	private ServerSocket sever;

	public void start(){
		try {
			sever = new ServerSocket(8888);
			recive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void recive(){
		try {
			Socket client = sever.accept();
			BufferedReader br = new BufferedReader(
								new InputStreamReader(
								client.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String msg="";
			while((msg=br.readLine()).length()>0){
				sb.append(msg);
				sb.append("\r\n");
			}
			System.out.println(sb.toString().trim());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		Sever1 sever = new Sever1();
		sever.start();
	}

}
