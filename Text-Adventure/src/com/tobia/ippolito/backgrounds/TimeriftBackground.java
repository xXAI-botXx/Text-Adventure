package com.tobia.ippolito.backgrounds;

import java.awt.Graphics;
import java.util.ArrayList;

import com.tobia.ippolito.game.Control;

public class TimeriftBackground {

	//variablen
	private Control control;
	private ArrayList<Star> stars = new ArrayList<Star>();
	
	public TimeriftBackground(Control control) {
		this.control = control;
		starsShouldBorn();
	}
	
	private void starsShouldBorn() {
		for(int i = 0; i < 30; i++) {
			stars.add(new Star(control));		
		}
		
		for(Star s:stars) {
			s.setStars(stars); 
		}
	}
	
	public void draw(Graphics g) {	//update und draw -> könnte man auch trennen, falls der thread überlastet wäre
		for(Star s:stars) {
			s.draw(g);
		}
	}
	
	public void update() {
		for(Star s:stars) {
			s.update();
		}
	}
}


