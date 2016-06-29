package com.meujogo.main.resources;

import java.awt.Color;
import java.awt.Graphics;

public class Bezier2 {
	
	private Reta retaR;
	private Reta retaAB;
	private Reta retaBC;
	private Ponto p;
	
	public Bezier2(float xInicial, float yInicial){
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
		
		p = new Ponto();
	}
	
	public void update(float t){
		retaR.start.setX((int) ((1-t)*retaAB.start.getX() + t* retaAB.end.getX()));
		retaR.start.setY((int) ((1-t)*retaAB.start.getY() + t* retaAB.end.getY()));
		
		retaR.end.setX((int) ((1-t)*retaBC.start.getX() + t* retaBC.end.getX()));
		retaR.end.setY((int) ((1-t)*retaBC.start.getY() + t* retaBC.end.getY()));
		
		float rx1,ry1,rx2,ry2, temp;
		
		rx1 = retaR.start.getX();
		ry1 = retaR.start.getY();
		rx2 = retaR.end.getX();
		ry2 = retaR.end.getY();
		
		temp = (1-t)*rx1 + t*rx2;
		p.setX(temp);
		
		temp = (1-t)*ry1 + t*ry2;
		p.setY(temp);
	}

	public void render(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine((int)retaR.start.getX(), (int)retaR.start.getY(), (int)retaR.end.getX(), (int)retaR.end.getY());
		g.setColor(Color.YELLOW);
		g.drawLine((int)retaAB.start.getX(), (int)retaAB.start.getY(), (int)retaAB.end.getX(), (int)retaAB.end.getY());
		g.drawLine((int)retaBC.start.getX(), (int)retaBC.start.getY(), (int)retaBC.end.getX(), (int)retaBC.end.getY());
		g.setColor(Color.BLUE);
		g.fillRect(0, 0,(int)p.getX(), (int)p.getY() );
	}

}
