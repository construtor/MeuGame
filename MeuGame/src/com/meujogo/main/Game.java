package com.meujogo.main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.meujogo.main.resources.Bezier;
import com.meujogo.main.resources.Bezier2;

public class Game extends Canvas implements Runnable {

	public final static String TITLE = "GAME";
	private boolean running=false;
	private ExecutorService executor;
	public static int width;
	public static int height;
	private Bezier2 linha;
	
	public Game(int w, int h){
		
		executor = Executors.newFixedThreadPool(1);
		width = w;
		height = h;
		linha = new Bezier2(50,50);
	}
	
	@Override
	public void run() {
		long startTime = 0;
		double perTick=1000/60;
		double num = 0;
		long count=0;
		while(running){
			
			startTime = System.currentTimeMillis();
			//////////////////////////////////////
			
			count+=num;
			segundo+=num;
				
				if (count>=perTick){
					count=0;
					fps++;
					second();
					update();
					render();
			}
			//////////////////////////////////////
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			num=System.currentTimeMillis()-startTime;
			
		}
	}
	
	private int fps=0;
	private int segundo=0;
	
	
	private void second() {
		
		if (segundo>=1000){
			//System.out.printf("\tFPS : %d\n",fps);
			fps=0;
			segundo=0;
		}
	}

	public boolean isRunning() {
		return running;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void encerra(){
		setRunning(false);
		executor.shutdown();
	}
	
	public void inicia(){
		setRunning(true);
		executor.execute(this);
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if (bs==null){
			createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		/////////////////////////////////
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, width, height);
		linha.render(g);
		////////////////////////////////
		
		g.dispose();
		bs.show();
	}
	
	private static float t;
	
	public static void setT(float t) {
		Game.t = t;
	}

	public void update(){
		System.out.println(t);
		linha.update(t);
		
	}
}
