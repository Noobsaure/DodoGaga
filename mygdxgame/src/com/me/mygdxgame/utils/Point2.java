package com.me.mygdxgame.utils;

public abstract class Point2<T>{
	
	public T x;
	public T y;
	
	public Point2(T x, T y){
		this.x = x;
		this.y = y;
	}
	
	public void set(T x, T y){
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return "Point2(" + x + ", " + y + ")";
	}
	//public boolean equals(Point2<T> p) {
	//	return p.x == x && p.y == y;
	//}
    
}
