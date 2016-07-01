package com.meujogo.main.resources;

import java.awt.Point;

public class Ponto {

	private double x;
	private double y;
	
	public Ponto() {
		
	}
	
	public Ponto(Point ponto){
		setX(ponto.x);
		setY(ponto.y);
	}
	
	public Ponto(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public Ponto(float x, float y) {
		setX(x);
		setY(y);
	}
	
	public float getX() {
		return (float) x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public float getY() {
		return (float) y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
}
