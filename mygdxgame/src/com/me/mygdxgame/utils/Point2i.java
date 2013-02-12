package com.me.mygdxgame.utils;

public class Point2i extends Point2<Integer>{

	public Point2i(){
		super(0, 0);
	}
	
	public Point2i(int x, int y) {
		super(x, y);
	}

	public String getHashCode() {
		return x + "," + y;
	}
	
	public boolean equals(Point2i p) {
		return this.x == p.x && this.y == p.y;
	}
}
