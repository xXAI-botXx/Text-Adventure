package com.tobia.ippolito.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.tobia.ippolito.story.*;

public class Control extends JPanel{

	//Variablen
	private JFrame frame;
	private Swinger swinger;
	
	private final int UPDATES_PER_SECOND = 30;
	private final long UPDATE_PERIOD_NSEC = 1000000000L / UPDATES_PER_SECOND;
	private Boolean run = true;
	
	static enum GameState{
		STARTMENU, STORYCHOOSE, PLAYING, CREDITS
	}
	
	static GameState state;		//zustände -> öffentlich
	private Story story;
	
	public Control(JFrame frame) {
		this.frame = frame;
		//this.setPreferredSize(new Dimension(800, 600));
		
		swinger = new Swinger(frame, this);
		add(swinger);
		addKeyListener(swinger);
		setFocusable(true);	//so that can receive key-events
		
		requestFocus();
		run();
	}
	
	public void run() {
		state = GameState.STARTMENU;
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				gameLoop();
			}
		});
		
		t.start();
	}
	
	public void gameLoop() {
		long beginTime, timetaken, timeLeft;
		
		while(run) {
			beginTime = System.nanoTime();
			
			update();
			repaint();
			
			//Warten -> um die Wiederholungsrate korrekt aufrecht zu erhalten
			timetaken = System.nanoTime() - beginTime;
			timeLeft = (UPDATE_PERIOD_NSEC - timetaken) / 1000000L;		//im Millisekunden 
			if(timeLeft < 10) timeLeft = 10;	//Minimum an Wartezeit
			try {
				Thread.sleep(timeLeft);
			} catch(InterruptedException e) {System.out.println("Thread wurde beim Warten unterbrochen");}
		}
	}
	
	public void update() {
		if(state == GameState.PLAYING) {
			story.update();
		}
	}
	
	public void draw(Graphics g) {
		if(state == GameState.STARTMENU || state == GameState.STORYCHOOSE) {
			
		}else if(state == GameState.PLAYING) {
			story.draw(g);
		}
	}
	
	public void inputStartmenu(String input) {
		String s = "";
		
		if(input.equalsIgnoreCase("Storychoose") || input.equalsIgnoreCase("choose") || input.equalsIgnoreCase("story") || input.equalsIgnoreCase("stories") || input.equalsIgnoreCase("story's") || input.equalsIgnoreCase("storys")) {
			s = "Stories:\n\nTest\nTimerift";
			takeMeldung("Keine Meldungen");
			state = GameState.STORYCHOOSE;
		}else if(input.equalsIgnoreCase("Credits")){
			s = "xX Programmer: Tobia Ippolito Xx\nxX Designer: Matteo Ippolito Xx";
			takeMeldung("Keine Meldungen");
			state = GameState.CREDITS;
		}else if(input.equalsIgnoreCase("Exit") || input.equalsIgnoreCase("X")){
			System.exit(0);
		}else if(input.equals("")){
			s = "Du hast nichts eingegeben!\nSiehe dir rechts nochmals die Key-Wörter an.";
			takeMeldung(s);
			return;
		}else {
			s = "'"+input+"'\nscheint kein Keyword zu sein!\nSiehe dir rechts nochmals die Key-Wörter an.";
			takeMeldung(s);
			return;
		}
		
		takeOutput(s);
	}
	
	public void inputStorychoose(String input) {
		String s = "";
		
		if(input.equalsIgnoreCase("Test")) {
			story = new TestStory(this);
			s = story.getText();
			takeMeldung("Keine Meldungen");
			state = GameState.PLAYING;
		}else if(input.equalsIgnoreCase("Timerift")){
			story = new TimeriftStory(this);
			s = story.getText();
			takeMeldung("Keine Meldungen");
			state = GameState.PLAYING;
			swinger.setBackgroundNow(new Color(72, 118, 255));
		}else if(input.equalsIgnoreCase("Back") || input.equalsIgnoreCase("<")){
			s = "Willkommen im Startmenü! Wähle einer der folgenden Optionen aus:\n\n\nStory's\nCredits\nExit";
			takeMeldung("Keine Meldungen");
			state = GameState.STARTMENU;
		}else if(input.equalsIgnoreCase("Exit") || input.equalsIgnoreCase("X")){
			System.exit(0);
		}else if(input.equals("")){
			s = "Du hast nichts eingegeben!\nSiehe dir rechts nochmals die Key-Wörter an.";
			takeMeldung(s);
			return;
		}else {
			s = "'"+input+"'\nscheint kein Keyword zu sein!\nSiehe dir rechts nochmals die Key-Wörter an.";
			takeMeldung(s);
			return;
		}
		
		takeOutput(s);
	}
	
	public void inputCredits(String input) {
		String s = "";
		
		if(input.equalsIgnoreCase("Tobia")) {
			s = "Tobia...";
			takeMeldung("Keine Meldungen");
		}else if(input.equalsIgnoreCase("Matteo")){
			s = "Matteo...";
			takeMeldung("Keine Meldungen");
		}else if(input.equalsIgnoreCase("Back") || input.equalsIgnoreCase("Startmenü") || input.equalsIgnoreCase("Menü")){
			s = "Willkommen im Startmenü! Wähle einer der folgenden Optionen aus:\n\n\nStory's\nCredits\nExit";
			takeMeldung("Keine Meldungen");
			state = GameState.STARTMENU;
		}else if(input.equalsIgnoreCase("Exit") || input.equalsIgnoreCase("X")){
			System.exit(0);
		}else if(input.equals("")){
			s = "Du hast nichts eingegeben!\nSiehe dir rechts nochmals die Key-Wörter an.";
			takeMeldung(s);
			return;
		}else {
			s = "'"+input+"'\nscheint kein Keyword zu sein!\nSiehe dir rechts nochmals die Key-Wörter an.";
			takeMeldung(s);
			return;
		}
		
		takeOutput(s);
	}
	
	public void takeInput(String s) {
		if(state == GameState.STARTMENU) {
			inputStartmenu(s);
			swinger.updateKeys();
		}else if(state == GameState.STORYCHOOSE){
			inputStorychoose(s);
			swinger.updateKeys();
		}else if(state == GameState.CREDITS){
			inputCredits(s);
			swinger.updateKeys();
		}else if(state == GameState.PLAYING){
			story.update(s);
		}
	}
	
	public void takeOutput(String output) {
		swinger.setOutput(output);
	}
	
	public void takeMeldung(String meldung) {
		swinger.setMeldung(meldung);
	}
	
	public void setKeys() {
		if(state == GameState.STARTMENU) {
			swinger.setKeys("Story\nCredits\nExit");
		}else if(state == GameState.STORYCHOOSE){
			swinger.setKeys("Test\nTimerift\nBack");
		}else if(state == GameState.CREDITS){
			swinger.setKeys("Back\nTobia\nMatteo\nExit");
		}else if(state == GameState.PLAYING){
			swinger.setKeys(story.getKeys());
		}
	}
	
	public void setOutput(String s) {
		swinger.setOutput(s);
	}

	
	public static void main(String[] args) {
		try {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					JFrame frame = new JFrame("Text Adventure");
					frame.setContentPane(new Control(frame));
					frame.setResizable(false);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
