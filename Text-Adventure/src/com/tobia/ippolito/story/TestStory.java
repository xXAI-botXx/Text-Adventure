package com.tobia.ippolito.story;

import java.awt.Graphics;

import com.tobia.ippolito.game.Control;

public class TestStory extends Story{

	private enum StoryLine{
		Gestartet, Einleitung1
	}
	
	private StoryLine line = StoryLine.Gestartet;
	
	
	public TestStory(Control control){
		super(control);
		
		actuallyText = "Willkommen in diesem Test!";
		keys = "next\nback";
	}
	
	@Override
	public void draw(Graphics g) {}
	
	@Override
	public void update() {}
	
	@Override
	public void update(String input) {		//input verarbeitung
		if(line == StoryLine.Gestartet) {
			gestartet(input);
		}else if(line == StoryLine.Einleitung1) {
			einleitung1(input);
		}
	}

	@Override
	public String getKeys() {		//keys
		return keys;
	}
	
	//Input-Handler: Szenarios
	private void gestartet(String input) {
		if(input.equalsIgnoreCase("next")) {
			line = StoryLine.Einleitung1;
			actuallyText = "Nun sind wir im zweiten Bereich. Die Story wird hier nun mit einer unvergleichlichen Wortgewandtheit\nerzählt.";
			keys = "Töten\nLeben lassen";
			updateKeys();
			updateText();
		}
	}
	
	private void einleitung1(String input) {
		
	}
}
