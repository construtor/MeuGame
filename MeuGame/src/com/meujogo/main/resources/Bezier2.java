package com.meujogo.main.resources;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Bezier2 {
	
	private Reta retaR;
	private Reta retaAB;
	private Reta retaBC;
	private Ponto p;
	private List <Ponto> parabola;
	private int suavidade;
	
	public Bezier2(float xInicial, float yInicial, int suave){
		retaR = new Reta();
		retaAB = new Reta();
		retaBC = new Reta();
		
		retaAB.start.setX(xInicial);
		retaAB.start.setY(yInicial);
		retaAB.end.setX(xInicial + 50);
		retaAB.end.setY(yInicial + 100);
		
		retaBC.start.setX(100);
		retaBC.start.setY(150);
		retaBC.end.setX(150);
		retaBC.end.setY(50);
		
		suavidade = suave;
		
		parabola = new ArrayList<Ponto>();
		
		p = new Ponto();
		
		
		addPontosParabola();
	}
	
	private void init(float t){
		retaR.start.setX((int) coordenada(t, retaAB.start.getX(), retaAB.end.getX()));
		retaR.start.setY((int) coordenada(t, retaAB.start.getY(), retaAB.end.getY()));
		
		retaR.end.setX((int) coordenada(t, retaBC.start.getX(), retaBC.end.getX()));
		retaR.end.setY((int) coordenada(t, retaBC.start.getY(), retaBC.end.getY()));
		
		float rx1,ry1,rx2,ry2;
		
		rx1 = retaR.start.getX();
		ry1 = retaR.start.getY();
		rx2 = retaR.end.getX();
		ry2 = retaR.end.getY();
		
		p.setX(coordenada(t, rx1, rx2));
		p.setY(coordenada(t, ry1, ry2));
	}
	
	public void update(float t){
		init(t);
	}
	
	private float coordenada(float t, float rx1, float rx2){
		return (1-t)*rx1 + t*rx2;
	}
	
	private void addPontosParabola(){
		
		float p=0;
		p = (float)1/suavidade;
		
		for (float t=0; t<1+p; t+=p){
			init(t);
			
			parabola.add(new Ponto(
					
					coordenada(t, retaR.start.getX(),retaR.end.getX()),
					coordenada(t, retaR.start.getY(),retaR.end.getY())
					
					));
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine((int)retaR.start.getX(), (int)retaR.start.getY(), (int)retaR.end.getX(), (int)retaR.end.getY());
		g.setColor(Color.YELLOW);
		g.drawLine((int)retaAB.start.getX(), (int)retaAB.start.getY(), (int)retaAB.end.getX(), (int)retaAB.end.getY());
		g.drawLine((int)retaBC.start.getX(), (int)retaBC.start.getY(), (int)retaBC.end.getX(), (int)retaBC.end.getY());
		g.setColor(Color.BLUE);
		g.fillRect((int)p.getX(), (int)p.getY() ,6, 6);
		
		g.setColor(Color.WHITE);
		for(int i=0; i<parabola.size()-1; i++){
			g.drawLine((int)parabola.get(i).getX(), (int)parabola.get(i).getY(), 
					(int)parabola.get(i+1).getX(), (int)parabola.get(i+1).getY());
		}
	}

}
