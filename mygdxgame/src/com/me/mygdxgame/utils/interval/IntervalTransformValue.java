package com.me.mygdxgame.utils.interval;

public class IntervalTransformValue {

	public float x;
	public float y;
	public float z;
	public float t;
	
	public IntervalTransformValue(){
		this(0);
	}
	
	public IntervalTransformValue(float x){
		this(x, 0);
	}
	
	public IntervalTransformValue(float x, float y){
		this(x, y, 0);
	}
	
	public IntervalTransformValue(float x, float y, float z){
		this(x, y, z, 0);
	}
	
	public IntervalTransformValue(float x, float y, float z, float t){
		this.x = x;
		this.y = y;
		this.z = z;
		this.t = t;
	}
	
	public void set(float x, float y, float z, float t){
		this.x = x;
		this.y = y;
		this.z = z;
		this.t = t;
	}
	
}
