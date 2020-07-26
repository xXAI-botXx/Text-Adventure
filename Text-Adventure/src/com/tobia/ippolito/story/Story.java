package com.tobia.ippolito.story;

import java.awt.Graphics;

import com.tobia.ippolito.game.Control;

public abstract class Story {

	//Variablen
	protected String actuallyText;
	protected String meldung;
	protected String keys;
	protected Control control;
	
	public Story(Control control) {
		this.control = control;
	}
	
	public abstract void draw(Graphics g);
	
	public abstract void update();
	
	public abstract void update(String input);
	
	public abstract String getKeys();
	
	public String getText() {
		return actuallyText;
	}
	
	public void updateKeys() {
		control.setKeys();
	}
	
	public void updateText() {
		control.setOutput(actuallyText);
	}
}
