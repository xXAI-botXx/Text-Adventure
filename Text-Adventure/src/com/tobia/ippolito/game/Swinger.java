package com.tobia.ippolito.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.tobia.ippolito.story.Story;

public class Swinger extends JPanel implements KeyListener{

	private JFrame frame;
	private Control control;
	private final int startWidth = 1000, startHeight = 600;
	private final int endWidth = 1200, endHeight = 600;
	private int width = 1000, height = 600;
	private boolean keyShow = false;
	private Story story;
	private Color cBackground = Color.BLACK;
	private Color cBackground1 = new Color(255, 215, 0);	//gold - gut
	private Color cBackground2 = new Color(141, 238, 238);	//türkis
	private Color cBackground3 = new Color(118, 238, 198);	//grün (-blau)
	private Color cBackground4 = new Color(202, 225, 255);	//himmelblau - gut
	private Color cBackground5 = new Color(240, 255, 240);	//leichtes gelb - gut
	private Color cBackground6 = new Color(255, 240, 245);	//leichtes pink - gut
	private Color cBackground7 = new Color(255, 250, 205);	//gelb - gut
	private Color cBackground8 = new Color(193, 255, 193);	//grün
	
	//GUI
	private JButton btnKeys;
	private JButton btnRun;
	private JTextArea txtKeys;
	private JTextArea txtOutput;
	private JTextArea txtMeldung;
	private JTextField txtInput;
	private JScrollPane scrollOutput;
	private JScrollPane scrollMeldung;
	private JScrollPane scrollKeys;
	
	public Swinger(JFrame frame, Control control) {
		this.frame = frame;
		this.control = control;
		
		this.setPreferredSize(new Dimension(width, height));
		setFocusable(true);	//so that can receive key-events
		requestFocus();
		
		this.setLayout(null);
		setRandomColor();
		
		//Swing Elemente
		btnKeys = new JButton("Keys >>");	
		btnKeys.setBounds(890, 50, 100, 50);
		btnKeys.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(keyShow) {
					keyShow = false;
					btnKeys.setText("Keys >>");
					width = startWidth;
					height = startHeight;
					setPreferredSize(new Dimension(width, height));
					frame.pack();
					txtKeys.setVisible(false);
				}else {
					keyShow = true;
					btnKeys.setText("<< Keys");
					width = endWidth;
					height = endHeight;
					setPreferredSize(new Dimension(width, height));
					frame.pack();
					control.setKeys();
					txtKeys.setVisible(true);
					//control.showKeys();
				}
			}
		});
		btnKeys.setBackground(new Color(171, 130, 255));
		btnKeys.setFocusable(false);
		btnKeys.setVisible(true);
		add(btnKeys);
		
		
		btnRun = new JButton("Run");	
		btnRun.setBounds(480, 450, 100, 50);
		btnRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				control.takeInput(txtInput.getText());
				txtInput.setText("");
			}
		});
		btnRun.setBackground(new Color(255, 106, 106));
		btnRun.setFocusable(false);
		btnRun.setVisible(true);
		add(btnRun);
		
		//textfelder
		txtKeys = new JTextArea();
		txtKeys.setBounds(1020, 50, 160, 500);
		txtKeys.setBackground(cBackground);
		txtKeys.setEditable(false);
		txtKeys.setFont(new Font("Dialog", Font.BOLD, 13));
		txtKeys.setVisible(false);
		add(txtKeys);
		
		txtOutput = new JTextArea();
		txtOutput.setBounds(50, 50, 800, 300);
		txtOutput.setBackground(cBackground);
		txtOutput.setText("Willkommen im Startmenü! Wähle einer der folgenden Optionen aus:\n\n\nStory's\nCredits\nExit");
		txtOutput.setEditable(false);
		txtOutput.setFont(new Font("Dialog", Font.BOLD, 15));
		txtOutput.setVisible(true);
		add(txtOutput);
		
		txtMeldung = new JTextArea();
		txtMeldung.setBounds(660, 410, 320, 140);
		txtMeldung.setBackground(cBackground);
		txtMeldung.setText("Keine Meldungen");
		txtMeldung.setEditable(false);
		txtMeldung.setFont(new Font("Dialog", Font.BOLD, 13));
		txtMeldung.setVisible(true);
		add(txtMeldung);
		
		txtInput = new JTextField();
		txtInput.setBounds(50, 450, 400, 50);
		txtInput.addKeyListener(this);
		txtInput.setVisible(true);
		add(txtInput);
		
		//Scroll-Bars
		scrollOutput = new JScrollPane(txtOutput);
		scrollOutput.setBounds(50, 50, 800, 300);
		scrollOutput.setBorder(null);
		scrollOutput.setVisible(true);
		add(scrollOutput);
		txtOutput.setCaretPosition(0);
		
		scrollMeldung = new JScrollPane(txtMeldung);
		scrollMeldung.setBounds(660, 410, 320, 140);	//170
		scrollMeldung.setBorder(null);
		scrollMeldung.setVisible(true);
		add(scrollMeldung);
		txtMeldung.setCaretPosition(0);
		
		scrollKeys = new JScrollPane(txtKeys);
		scrollKeys.setBounds(1020, 50, 150, 500);
		scrollKeys.getHorizontalScrollBar().setValue(0);
		scrollKeys.setBorder(null);
		add(scrollKeys);
	}
	
	@Override
	public void paintComponent(Graphics g) {	//sollen über textAreas gezeichnet werden!
		super.paintComponent(g);
		g.clearRect(0, 0, width, height);
		
		g.setColor(cBackground);
		g.fillRect(0, 0, width, height);
		
		control.draw(g);
		
		//Füllung der Textfelder: damit der Hintergrund nicht zu sehen ist
		g.setColor(cBackground);
		g.fillRect(40, 40, 820, 320);	
		
		g.fillRect(650, 400, 340, 160);		//190
		
		if(keyShow) {
			g.fillRect(1010, 40, 170, 520);
		}
		
		//Rahmen der textfelder
		g.setColor(Color.BLACK);
		g.drawRect(40, 40, 820, 320);	
		
		g.drawRect(650, 400, 340, 160);		//190
		
		if(keyShow) {
			g.drawRect(1010, 40, 170, 520);
		}
	}
	
	public void setStory(Story story) {
		this.story = story;
	}
	
	public void setOutput(String s) {
		txtOutput.setText(s);
		txtOutput.setCaretPosition(0);
	}
	
	public void setMeldung(String s) {
		txtMeldung.setText(s);
		txtMeldung.setCaretPosition(0);
	}
	
	public void setKeys(String keys) {
		txtKeys.setText(keys);
		txtKeys.setCaretPosition(0);
	}
	
	public void updateKeys() {
		control.setKeys();
	}
	
	public void setRandomColor() {
		int rand = (int) (Math.random()*(10-1)) + 1;	//1 - 10
		switch(rand) {
		case 1:	cBackground = cBackground1;
			break;
		case 2:	cBackground = cBackground2;
			break;
		case 3:	cBackground = cBackground3;
			break;
		case 4: cBackground = cBackground4;
			break;
		case 5: cBackground = cBackground5;
			break;
		case 6: cBackground = cBackground6;
			break;
		case 7: cBackground = cBackground7;
			break;
		case 8: cBackground = cBackground8;
			break;
		}
	}
	
	public void setBackgroundNow(Color c) {
		cBackground = c;
		txtKeys.setBackground(c);
		txtOutput.setBackground(c);
		txtMeldung.setBackground(c);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeyEvent.VK_ESCAPE: 
			System.exit(0);
			break;
		case KeyEvent.VK_ENTER: 
			control.takeInput(txtInput.getText());
			txtInput.setText("");
			break;
		case KeyEvent.VK_UP:
			//txtOutput.setCaretPosition(txtOutput.getCaretPosition()-50);
			break;
		case KeyEvent.VK_DOWN:
			//txtOutput.setCaretPosition(txtOutput.getCaretPosition()+50);
			break;
		default: txtInput.grabFocus();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
