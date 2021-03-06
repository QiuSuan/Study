package mycastle;

import java.util.HashMap;

public class Room {
	private String name;
	private int weather;
	private HashMap<String ,Room> doors =new HashMap<String ,Room>(); 
	protected HashMap<Integer,String> weathers = new HashMap<Integer,String>();
	public Room(String name) {
		this.name=name;
		this.weather = (int)(Math.random()*4);
		weathers.put(0, "����");
		weathers.put(1, "����");
		weathers.put(2, "����");
		weathers.put(3, "����");
	}
	
	public void setDoor(String dir,Room room){
		doors.put(dir, room);
	}
	
	public String showName(){
		return this.name;
	}
	public int showweather(){
		return this.weather;
	}
	
	public String showDoor(){
		StringBuffer sd = new StringBuffer();
		for(String door : doors.keySet()){
			sd.append(door);
			sd.append(" ");
		}
		return sd.toString();
	}
	
	public Room exit(String dir){
		if(doors.get(dir)!=null){
			this.weather = (int)(Math.random()*4);
		}
		return doors.get(dir);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
