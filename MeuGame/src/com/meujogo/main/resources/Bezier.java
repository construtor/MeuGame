package com.meujogo.main.resources;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Bezier {

	private Point inicio;
	private Point controle;
	private Point fim;
	private int suave;
	
	private List < Point > retaR;
	
	public Bezier(Point inicio, Point controle, Point fim, int pontos) {
		super();
		this.inicio = inicio;
		this.controle = controle;
		this.fim = fim;
		suave = pontos;
		
		retaR = new ArrayList<Point> ();
	}
	
	private void processa(int pontos){
		int ptx = (int) (inicio.getX()-controle.getX())/pontos;
		int pty = (int) (inicio.getY()-controle.getY())/pontos;
		
		addPontos(Math.abs(ptx), Math.abs(pty));
	}

	private void addPontos(int ptx, int pty) {
		for(int i=0; i<suave; i++){
			retaR.add(new Point(inicio.x+(i*ptx),inicio.y+(i*pty)));
		}
	}
	
	public void render(Graphics g){
		processa(suave);
		
		Point point = new Point();
		point = retaR.get(1);
		System.out.println(Integer.toString((int)point.getX())+"  "+Integer.toString(point.y));
		g.drawLine((int)point.getX(), (int)point.getY(),(int) point.getX()+40, (int)point.getX()+40);
	}
}
