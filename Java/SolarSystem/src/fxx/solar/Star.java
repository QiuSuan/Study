package fxx.solar;

import java.awt.Graphics;
import java.awt.Image;

import fxx.util.GameUtil;

public class Star {
	protected Image I;
	protected double x;
	protected double y;
	protected double width;
	protected double hight;
	
	public Star(){}
	public Star(Image i){
		I=i;
		this.width = I.getWidth(null);
		this.hight = I.getHeight(null);
	}
	public Star(Image i, double x, double y) {
		this(i);
		this.x = x;
		this.y = y;
	}
	public Star(String imgPath, double x, double y) {
		this(GameUtil.getImage(imgPath),x,y);
	}
	public void draw(Graphics g){
		g.drawImage(I, (int)(x-width/2), (int)(y-hight/2), null);
	}
}
