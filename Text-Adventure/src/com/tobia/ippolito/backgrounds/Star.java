package com.tobia.ippolito.backgrounds;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.tobia.ippolito.game.Control;

public class Star {

	//Variablen
	private float speed;
	private float x, y;		//mittelpunkt
	private int radius, durchmesser;
	private Control control;
	private ArrayList<Star> stars;
	private ArrayList<Star> starNears = new ArrayList<Star>();
	private Boolean starsGet = false;
	
	public Star(Control control) {
		this.control = control;
		
		speed = 1f;
		radius = (int) (Math.random()*(10-5)+5);	//5-10
		durchmesser = radius * 2;
		x = (float) (Math.random()*(control.getWidth()-  100 -(-100)) + (-100));	//-100 - 0
		y = (float) (Math.random()*(600-radius-radius)+ radius);	//radius - 600-radius
	}
	
	public void aStarShouldBorn() {
		speed = 1f;
		radius = (int) (Math.random()*(10-3)+3);	//3-10
		durchmesser = radius * 2;
		x = (float) (Math.random()*(0-(-500)) + (-500));	//-100 - 0
		y = (float) (Math.random()*(600-radius-radius)+ radius);	//radius - 600-radius
	}
	
	public void draw(Graphics g) {	//erst updaten, hier leider direkt vor dem Zeichnen -> damit hält man den AWT-Event-Thread auf, aber man hat ja nicht viel bei diesem game!s
		g.setColor(Color.WHITE);
		g.fillOval((int) x-radius, (int) y-radius, durchmesser, durchmesser);
		
		if(starsGet) {
			for(Star s:starNears) {
				g.drawLine((int) x, (int) y, (int) s.getX(), (int) s.getY());
			}
		}
	}
	
	public void update() {
		x += speed;
		
		if(x-radius >= control.getWidth()) {	//funktioniert control.getWidth?
			aStarShouldBorn();
		}
		
		if(starsGet) {	//überprüfe sterne in der nähe -> array wird neu aufgesetzt
			starNears.clear();
			
			for(Star s:stars) {
				float xDistance = Math.abs(x - s.getX());
				float yDistance = Math.abs(y - s.getY());
				
				if(yDistance <= 100 && xDistance <= 100) {
					starNears.add(s);
				}
			}
		}
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setStars(ArrayList<Star> stars) {
		this.stars = stars;
		starsGet = true;
	}
}
