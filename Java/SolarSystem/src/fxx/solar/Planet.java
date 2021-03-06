package fxx.solar;




import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import fxx.util.Constant;
import fxx.util.GameUtil;


public class Planet extends Star{
	
	protected Star center;
	protected double longaxis;
	protected double shortaxis;
	protected double degree=Math.PI/2;
	protected double speed;
	protected boolean satellite =false ;

	public Planet(String imgPath,Star center,double longaxis,double shortaxis,double speed){
		super(GameUtil.getImage(imgPath));
		this.center=center;
		this.longaxis=longaxis;
		this.shortaxis=shortaxis;
		this.speed =speed;
		this.x = center.x+longaxis;
		this.y=center.y;
	}
	
	public Planet(String imgPath,Star center, double longaxis, double shortaxis, double speed, boolean satellite) {
		this(imgPath,center,longaxis,shortaxis,speed);
		this.satellite = satellite;
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		if(!satellite){
			drawOval(g);
		}
		move();
	}
	
	public void drawOval(Graphics g){
		double ovalX,ovalY,ovalWidth,ovalHight;
		ovalX=center.x-this.longaxis-this.width/2;
		ovalY=center.y-this.shortaxis-this.hight/2;
		ovalWidth=2*this.longaxis;
		ovalHight=2*this.shortaxis;
		Color c= g.getColor();
		g.setColor(new Color(0,0,255));
		g.drawOval((int)ovalX, (int)ovalY, (int)ovalWidth, (int)ovalHight);
		g.setColor(c);
	}
	
	public void move(){
		this.degree+=speed;
		this.x=center.x +longaxis*Math.cos(degree);
		this.y=center.y +shortaxis*Math.sin(degree);
	}
	
	
	
}
