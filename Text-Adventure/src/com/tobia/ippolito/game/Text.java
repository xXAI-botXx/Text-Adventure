package com.tobia.ippolito.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Text {

	private int x,y;
	private int width, height;
	private Font font = new Font("Dialog", Font.BOLD, 13);
	private String text = "";
	private ArrayList<String> textAreas = new ArrayList<String>();
	private int actuelPage = 0;
	private Button btnLeft;
	private Button btnRight;
	private int maxLineLength;
	private Boolean maxSets = false;
	
	public Text() {
		initButtons();
	}
	
	public Text(String text) {
		this.text = text;
		initButtons();
	}
	
	public Text(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		initButtons();
	}
	
	public Text(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		textAreas.add(text);
		
		initButtons();
	}
	
	public Text(int x, int y, int width, int height, Font font) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.font = font;
		
		initButtons();
	}
	
	public Text(int x, int y, int width, int height, String text, Font font) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		textAreas.add(text);
		
		initButtons();
	}
	
	private void initButtons() {
		btnLeft = (new Button(x+width/2 -25, y+height-25, 50, 50, "<<") {
			@Override
			public void clickAction() {
				if(actuelPage != 0) actuelPage --;
			}
		});
		btnLeft.setVisible(true);
		
		btnRight = (new Button(x+width/2 + 25, y+height-25, 50, 50, ">>") {
			@Override
			public void clickAction() {
				if(actuelPage != textAreas.size()-1) actuelPage ++;
			}
		});
		btnRight.setVisible(true);
	}
	
	public void setText(String s) {
		text = s;
		
		textAreas.clear();
		//text in bereiche einteilen
		int anzLines = text.split("\n").length + 1;
		String[] lines = text.split("\n");
		
		int count = 0;
		int anzPage = 1;
		String page = "";
		for(int i = 0; i < anzLines; i++) {
			if(count <= 14) {
				page = page+lines[i]+"\n";
				count++;
				textAreas.set(anzPage-1, page);
			}else {
				anzPage++;
				textAreas.add(page);
				count = 0;
				page = "";
			}
		}
		
		if(maxSets) {	
			for(String t:textAreas) {
				char[] newText = t.toCharArray();
				for(int i = 0; i < newText.length; i++) {
					if(i >= maxLineLength-3) {
						t = newText.toString()+"...";
					}
				}
			}
		}
	}
	
	public void drawText(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(font);
		
		g.drawString(textAreas.get(actuelPage), x, y);
		
		//if(actuelPage >= 0 && textAreas.size() > 1 && actuelPage != textAreas.size()-1) {	
			btnRight.draw(g);
		//}
		
		//if(actuelPage > 0) {
			btnLeft.draw(g);
		//}
	}
	
	public void setMaxLength(int max) {
		maxLineLength = max;
		maxSets = true;
	}
}
