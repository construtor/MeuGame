package com.meujogo.main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game extends Canvas implements Runnable {

	public final static String TITLE = "GAME";
	private boolean running=false;
	private ExecutorService executor;
	public static int width;
	public static int height;
	
	public Game(int w, int h){
		
		executor = Executors.newFixedThreadPool(1);
		width = w;
		height = h;
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
			/*try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			num=System.currentTimeMillis()-startTime;
		}
	}
	
	private int fps=0;
	private int segundo=0;
	
	private void second() {
		
		if (segundo>=1000){
			System.out.printf("\tFPS : %d\n",fps);
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
		//back.render(g, 0, 0);
		//sprite.render(g, sX, sY);
		
		////////////////////////////////
		
		g.dispose();
		bs.show();
	}
	
	public void update(){
		//KeyInput
		///////////////////////////////////////////////////
				
		
	}
}
