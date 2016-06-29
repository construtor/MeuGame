package com.meujogo.main;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main extends JFrame implements ChangeListener{
	
	private Game game;
	private static int WIDTH = 600;
	private static int HEIGHT = WIDTH/4*3;
	public static Main INSTANCE;
	private JSlider slider;
	
	
	public Main(){
		INSTANCE = this;
		setSize(WIDTH,HEIGHT);
		setResizable(false);
		setLocation(600,300);
		
		game = new Game(WIDTH,HEIGHT);
		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		JPanel panelswing = new JPanel();
		
		panelswing.setLayout(new FlowLayout(FlowLayout.RIGHT));
		slider = new JSlider();
		panelswing.add(slider);
		
		container.add(panelswing, BorderLayout.SOUTH);
		container.add(BorderLayout.CENTER,game );
		
		slider.addChangeListener(this);
		slider.setMaximum(100);
		slider.setMinimum(0);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				gameStop();
			}
		});
		
		setVisible(true);
		setFocusable(true);
		setAutoRequestFocus(true);
	}
	
	public void gameStop() {
		System.out.println("/////////////////\ngameStop acionado!\n/////////////////");
		game.encerra();
		System.exit(1);
	}
	
	public void gameStart(){
		game.inicia();
	}

	@Override
	public void stateChanged(ChangeEvent ev) {
		double i=slider.getValue();
		double tesao = i/100;
		
		Game.setT((float) tesao);
	}

	public static void main(String args[]){
		Main app = new Main();
		app.gameStart();
	}

}
