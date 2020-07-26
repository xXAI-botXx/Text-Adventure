package com.tobia.ippolito.story;

import java.awt.Graphics;

import com.tobia.ippolito.backgrounds.TimeriftBackground;
import com.tobia.ippolito.game.Control;

public class TimeriftStory extends Story{
	
		private enum StoryLine{
			Gestartet, Einleitung1, Einleitung2V1, Einleitung2V2, Einleitung2V3
		}
		
		private StoryLine line = StoryLine.Gestartet;
		private TimeriftBackground background;
		
		public TimeriftStory(Control control){
			super(control);
			
			background = new TimeriftBackground(control);
			
			actuallyText = "Wilkommen Abenteurer. Es erwartet dich eine Geschichte in einer unbekannten Welt und mit unbekannten\nFolgen.\r\n" + 
							"Entscheide mit bedacht!";
			keys = "next\nback";
		}
		
		@Override
		public void draw(Graphics g) {
			background.draw(g); 
		}
		
		@Override
		public void update() {
			background.update();
		}
		
		@Override
		public void update(String input) {		//input verarbeitung
			if(line == StoryLine.Gestartet) {
				gestartet(input);
			}else if(line == StoryLine.Einleitung1) {
				einleitung1(input);
			}else if(line == StoryLine.Einleitung2V1) {
				einleitung2V1(input);
			}else if(line == StoryLine.Einleitung2V2) {
				einleitung2V2(input);
			}else if(line == StoryLine.Einleitung2V3) {
				einleitung2V3(input);
			}
			
			//update keys and vie text
			updateKeys();
			updateText();
		}

		@Override
		public String getKeys() {		//keys
			return keys;
		}
		
		//Input-Handler: Szenarios
		private void gestartet(String input) {
			if(input.equalsIgnoreCase("next")) {
				line = StoryLine.Einleitung1;
				actuallyText = "Du nimmst einen leisen Glockenschlag wahr. Langsam wird dieser immer lauter. \r\n" + 
						"'Was ist das für ein Glockenschlag?... Zeit... Zeit!'. Du reißt deine Augen auf. \r\n" + 
						"Du hast wiedereinmal verschlafen und das genau heute.\r\n" + 
						"Hektisch ziehst du dir dein Hemd über, schlüpfst in die löchrige Hose und versuchst in der Eile deine Stiefel\nanzuziehen.\r\n" + 
						"Wie nicht anders zu erwarten, fällst du dabei zu Boden. Schnell wieder aufgerichtet klopfst du dir den Dreck aus\nden Klamotten.\r\n" + 
						"So schnell wie du nur du kannst machst dich auf zum Marktplatz.\r\n" + 
						"Du kannst schon die Meute hören und atmest innerlich auf.\r\n" + 
						"Im nächsten Moment liegst du am Boden. Die wütende Stimme eines Verkäufers prasselt auf dich nieder.\r\n" + 
						"In Gedanken versunken bist du wohl in einen Verkaufsstand gerannt.\r\n" + 
						"\nWas willst du nun tun? Wegrennen? Entschuldigen?";
				keys = "Wegrennen\nEntschuldigen\nVerteidigen";
				control.takeMeldung("Keine Meldungen");
			}else if(input.equals("")){
				meldung = "Du hast nichts eingegeben!\nSiehe dir rechts nochmals die Key-Wörter an.";
				control.takeMeldung(meldung);
			}else {
				meldung = "'"+input+"'\nscheint kein Keyword zu sein!\nSiehe dir rechts nochmals die Key-Wörter an.";
				control.takeMeldung(meldung);
			}
		}
		
		private void einleitung1(String input) {
			if(input.equalsIgnoreCase("Wegrennen") || input.equalsIgnoreCase("rennen") || input.equalsIgnoreCase("run")) {
				line = StoryLine.Einleitung2V1;
				actuallyText = "";
				keys = "";
				control.takeMeldung("Keine Meldungen");
			}else if(input.equals("")){
				meldung = "Du hast nichts eingegeben!\nSiehe dir rechts nochmals die Key-Wörter an.";
				control.takeMeldung(meldung);
			}else {
				meldung = "'"+input+"'\nscheint kein Keyword zu sein!\nSiehe dir rechts nochmals die Key-Wörter an.";
				control.takeMeldung(meldung);
			}
		}
		
		private void einleitung2V1(String input) {
			if(input.equalsIgnoreCase("next")) {
				line = StoryLine.Einleitung1;
				actuallyText = "";
				keys = "";
				control.takeMeldung("Keine Meldungen");
			}else if(input.equals("")){
				meldung = "Du hast nichts eingegeben!\nSiehe dir rechts nochmals die Key-Wörter an.";
				control.takeMeldung(meldung);
			}else {
				meldung = "'"+input+"'\nscheint kein Keyword zu sein!\nSiehe dir rechts nochmals die Key-Wörter an.";
				control.takeMeldung(meldung);
			}
		}
		
		private void einleitung2V2(String input) {
			if(input.equalsIgnoreCase("next")) {
				line = StoryLine.Einleitung1;
				actuallyText = "";
				keys = "";
				control.takeMeldung("Keine Meldungen");
			}else if(input.equals("")){
				meldung = "Du hast nichts eingegeben!\nSiehe dir rechts nochmals die Key-Wörter an.";
				control.takeMeldung(meldung);
			}else {
				meldung = "'"+input+"'\nscheint kein Keyword zu sein!\nSiehe dir rechts nochmals die Key-Wörter an.";
				control.takeMeldung(meldung);
			}
		}
		
		private void einleitung2V3(String input) {
			if(input.equalsIgnoreCase("next")) {
				line = StoryLine.Einleitung1;
				actuallyText = "";
				keys = "";
				control.takeMeldung("Keine Meldungen");
			}else if(input.equals("")){
				meldung = "Du hast nichts eingegeben!\nSiehe dir rechts nochmals die Key-Wörter an.";
				control.takeMeldung(meldung);
			}else {
				meldung = "'"+input+"'\nscheint kein Keyword zu sein!\nSiehe dir rechts nochmals die Key-Wörter an.";
				control.takeMeldung(meldung);
			}
		}
}
