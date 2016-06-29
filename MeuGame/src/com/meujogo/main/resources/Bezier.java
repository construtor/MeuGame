package com.meujogo.main.resources;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Bezier {

	private Point inicio;
	private Point controle;
	private Point fim;
	private int suave;
	
	private List < Point > retaR;
	private List < Point > retaS;
	private List < Point > intersecao;
	
	public Bezier(Point inicio, Point controle, Point fim, int pontos) {
		super();
		this.inicio = inicio;
		this.controle = controle;
		this.fim = fim;
		suave = pontos;
		
		retaR = new ArrayList<Point> ();
		retaS = new ArrayList<Point> ();
		intersecao = new ArrayList<Point>();
		
		retaR.add(inicio);
		retaS.add(controle);
		
		//processa(suave);
		//insercaoPontos();
	}
	
	private void processa(int pontos){
		int ptx = (int) (inicio.getX()-controle.getX())/pontos;
		int pty = (int) (inicio.getY()-controle.getY())/pontos;
		
		addPontos(retaR, Math.abs(ptx), Math.abs(pty));
		
		ptx = (int) (fim.getX()-controle.getX())/pontos;
		pty = (int) (fim.getY()-controle.getY())/pontos;
		
		addPontos(retaS, ptx, pty);
		retaS.add(fim);
	}

	private void addPontos(List <Point> reta, int ptx, int pty) {
		Point ponto =reta.get(0);
		
		for(int i=1; i<suave; i++){
			
			reta.add(new Point(ponto.x+(i*ptx),ponto.y+(i*pty)));
		}
	}
	
	private Point intersecao(Point um, Point dois, Point tres, Point quat){
		int a1,b1,c1,a2,b2,c2,xis,yps;
		a1=um.y-dois.y;					a2=tres.y-quat.y;
		b1=dois.x-um.x;					b2=quat.x-tres.x;
		c1=(um.x*dois.y)-(dois.x*um.y);	c2=(tres.x*quat.y)-(quat.x*tres.y);
		
		yps = (int) (-a1*c2+a2*c1)/(-a2*b1+a1*b2);
		
		xis = (int) (-b1*yps-c1)/a1;
		
		
		return new Point(xis,yps);
	}
	
	public void insercaoPontos(){
		for (int i=0; i<suave-1; i++){
			intersecao.add(intersecao(retaR.get(i),retaS.get(i+1),retaR.get(i+1),retaS.get(i+2)));
			
		}
	}
	
	public abstract void render(Graphics g);
}
