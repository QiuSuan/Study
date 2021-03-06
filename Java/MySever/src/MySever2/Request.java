package MySever2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Request {
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	
	private InputStream is;
	
	Map<String,List<String>> clientInfo ;
	private String allInfo;
	private String method;
	private String url;
	private String request;
	
	public Request(){
		clientInfo = new HashMap<String,List<String>>();
		allInfo="";
		method="";
		url="";
		request="";
	}
	public Request(InputStream is) throws IOException{
		this();
		this.is=new BufferedInputStream(is);
		byte[] flush = new byte[20480];
		int len = is.read(flush);
		if(-1==len){
			return;
		}
		allInfo = new String(flush,0,len);
//		byte[] flush = new byte[1024];
//		int len =0;
//		StringBuilder sb = new StringBuilder();
//		try {
//			while(-1!=(len=this.is.read(flush))){
//				sb.append(new String(flush,0,len));
//			}
//		} catch (IOException e) {
//			allInfo=null;
//			return;
//		}
//		allInfo = (sb.toString()).trim();
//		System.out.println(allInfo);
		parseAllInfo();
	}
	public Request(Socket client) throws IOException{
		this(client.getInputStream());
	}
	
	private void parseAllInfo(){
		if(null==allInfo||allInfo.equals("")){
			return;
		}
		method = (allInfo.substring(0, allInfo.indexOf('/'))).trim();
		String urlStr = (allInfo.substring(allInfo.indexOf('/'),allInfo.indexOf("HTTP/"))).trim();
		if(method.equals("GET")){
			if(urlStr.contains("?")){
				url = (urlStr.substring(0,urlStr.indexOf('?'))).trim();
				request=(urlStr.substring(urlStr.indexOf('?')+1)).trim();
			}
		}else if(method.equals("POST")){
			url = urlStr;
			request=(allInfo.substring(allInfo.lastIndexOf(CRLF))).trim();
		}
		parseRequest();
	}
	
	private void parseRequest(){
		if(null==request||request.equals("")){
			return;
		}
		String[] requests = request.split("&");
		for(String str:requests){
			String[] keyValue = str.split("=");
			String key="";
			String value=null;
			if(keyValue.length==1){
				key = keyValue[0].trim();
			}
			else{
				key = keyValue[0].trim();
				value = decode(keyValue[1].trim(),"utf-8");
			}
			if(!clientInfo.containsKey(key)){
				ArrayList<String> valueList = new ArrayList<String>();
				valueList.add(value);
				clientInfo.put(key, valueList);
			}else{
				clientInfo.get(key).add(value);
			}
		}
	}
	private String decode(String value,String code){
		
		try {
			return java.net.URLDecoder.decode(value, code);
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	public String[] getClientValues(String key){
		List<String> values = clientInfo.get(key);
		if(null==values){
			return null;
		}else{
			return values.toArray(new String[0]);
		}
	}
	
	public String getClientValue(String key){
		List<String> values = clientInfo.get(key);
		if(null==values){
			return null;
		}else{
			return values.get(0);
		}
	}
	
	public String getURL(){
		return url;
	}
	
}
