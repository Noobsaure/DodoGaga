package com.me.mygdxgame.utils.interval.interfaces;

import com.badlogic.gdx.graphics.Color;
import com.me.mygdxgame.utils.Point2f;

public interface IntervalTransformable {

	public void setTransform(int type, float x, float y);
	public Point2f getTransform(int type); 
	//public Point2f getPosition();
	
}
