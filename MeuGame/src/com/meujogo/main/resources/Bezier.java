package com.meujogo.main.resources;

import java.awt.Color;
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
	private List < Point > retaS;
	
	public Bezier(Point inicio, Point controle, Point fim, int pontos) {
		super();
		this.inicio = inicio;
		this.controle = controle;
		this.fim = fim;
		suave = pontos;
		
		retaR = new ArrayList<Point> ();
		retaS = new ArrayList<Point> ();
		
		retaR.add(inicio);
		retaS.add(controle);
		
		processa(suave);
	}
	
	private void processa(int pontos){
		int ptx = (int) (inicio.getX()-controle.getX())/pontos;
		int pty = (int) (inicio.getY()-controle.getY())/pontos;
		
		addPontos(retaR, Math.abs(ptx), Math.abs(pty));
		
		ptx = (int) (fim.getX()-controle.getX())/pontos;
		pty = (int) (fim.getY()-controle.getY())/pontos;
		
		addPontos(retaS, ptx, pty);
		///retaS.add(fim);
	}

	private void addPontos(List <Point> reta, int ptx, int pty) {
		Point ponto =reta.get(0);
		
		for(int i=1; i<suave; i++){
			
			reta.add(new Point(ponto.x+(i*ptx),ponto.y+(i*pty)));
		}
	}
	
	public void render(Graphics g){
		
		//g.drawLine(inicio.x, inicio.y, controle.x, controle.y);
		//g.drawLine(controle.x, controle.y, fim.x, fim.y);
		for(int i=0; i<retaR.size(); i++){
			g.setColor(Color.BLUE);
			g.drawLine((int)retaR.get(i).getX(), (int)retaR.get(i).getY(), (int)retaS.get(i).getX(), (int)retaS.get(i).getY());
			
			g.setColor(Color.CYAN);
			g.drawString("Inicio", inicio.x, inicio.y);
			g.drawString("Controle", controle.x, controle.y);
			g.drawString("Fim", fim.x, fim.y);
		}
	}
}
