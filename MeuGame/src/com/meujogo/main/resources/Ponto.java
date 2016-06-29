package com.meujogo.main.resources;

import java.awt.Point;

public class Ponto {

	private float x;
	private float y;
	
	public Ponto(Point ponto){
		setX(ponto.x);
		setY(ponto.y);
	}
	
	public Ponto() {
		
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
}
