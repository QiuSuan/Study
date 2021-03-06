package mycastle;

import java.util.HashMap;
import java.util.Scanner;

public class Game {
	private Room currentroom ;
	private Water player;
	private HashMap<String ,Handler> handler =new HashMap<String ,Handler>();
	public Game()
	{
		creatRoom();
		handler.put("向", new HandlerGo(this));
		handler.put("再", new HandlerBye(this));
		handler.put("帮", new HandlerHelp(this));
		player = new Water(3);
	}
	
	public void creatRoom(){
		Room outside,lobby,hall,study,kitchen,toilet,bedroom,guestroom;
		outside=new Room("城堡外");
		lobby=new Room("走廊");
		hall=new Room("大厅");		
		study=new Room("书房");
		kitchen=new Room("厨房");
		toilet=new Room("厕所");
		bedroom=new Room("卧室");
		guestroom=new Room("客房");
		
		outside.setDoor("北", lobby);
		lobby.setDoor("北", hall);
		hall.setDoor("南", lobby);
		hall.setDoor("东", kitchen);
		hall.setDoor("北", toilet);
		hall.setDoor("西", study);
		study.setDoor("东", hall);
		kitchen.setDoor("西", hall);
		toilet.setDoor("东", guestroom);
		toilet.setDoor("南", hall);
		toilet.setDoor("西", bedroom);
		bedroom.setDoor("东", toilet);
		guestroom.setDoor("西", toilet);
		currentroom=outside;
	}
	public void wellcome(){
		System.out.println("欢迎来到小水滴冒险游戏");
		System.out.println("你将会遇到各种天气的房间，将自己变成冰，并返回城堡外则成功");
		System.out.println("可使用“帮助”，或“再见”");
		this.showprompt();
	}
	public void showprompt(){
		System.out.print("你现在在"+currentroom.showName()+"，");
		System.out.print("现在天气"+currentroom.weathers.get(currentroom.showweather())+"，");
		System.out.println("你现在状态是"+player.showstatu());
		System.out.println("出口方向有："+currentroom.showDoor());
		System.out.println("请使用方向语句如（向东）移动");
	}
	public void outdoor(String dir){
		if(currentroom.exit(dir)==null){
			System.out.println("无法向"+dir+"走");
		}
		else{
			this.currentroom = currentroom.exit(dir);
			player.change((this.currentroom.showweather()));
		}
		
		showprompt();
	}
	
	public void start(){
		Scanner in = new Scanner(System.in);
		while(true){
			String[] words = in.nextLine().split("");
			Handler hd =handler.get(words[0]);
			String value ="";
			if (words.length>1){
				value = words[1];
			}
			if(hd!=null){
				hd.doCmd(value);
				if(player.showstatu().equals("冰")&&currentroom.showName().equals("城堡外")||hd.bye()){
					if(player.showstatu().equals("冰")&&currentroom.showName().equals("城堡外")){
						System.out.println("算你狠，成功了");	
					}
					break;
				}
			}
		}
		System.out.println("感谢参与游戏");	
		in.close();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game();
		g.wellcome();
		g.start();
	}

}
