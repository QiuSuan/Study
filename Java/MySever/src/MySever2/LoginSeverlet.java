package MySever2;

import java.io.IOException;
/**
 * ��½ Severlet
 * @author ������
 *
 */
public class LoginSeverlet extends Severlet{
	
	
	@Override
	public void doSever(Request req, Response rep) {
		if((req.getClientValue("username")).equals("fxx")&&(req.getClientValue("password")).equals("123456")){
			this.doGet(req, rep);
			this.doPost(req, rep);
		}
		else{
			this.doFault(req, rep);
		}
				
	}
	public void doFault(Request req, Response rep){
		rep.contentPrintln("<html><head><title>��½ʧ��</title>");
		rep.contentPrintln("</head><body>");
		rep.contentPrintln("��¼ʧ��");
		rep.contentPrintln("</body></html>");
		try {
			rep.pushToClient(200);
		} catch (IOException e) {
			try {
				rep.pushToClient(500);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void doGet(Request req, Response rep){
		rep.contentPrintln("<html><head><title>��½�ɹ�</title>");
		rep.contentPrintln("</head><body>");
		rep.contentPrintln("��ӭ��"+req.getClientValue("username")+"����");
		rep.contentPrintln("</body></html>");
		try {
			rep.pushToClient(200);
		} catch (IOException e) {
			try {
				rep.pushToClient(500);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@Override
	public void doPost(Request req, Response rep) {
		// TODO Auto-generated method stub
		
	}

}
