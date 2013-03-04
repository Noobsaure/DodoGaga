package com.me.mygdxgame.utils.interval.transform;

import com.badlogic.gdx.math.Interpolation;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.interval.base.TimeBasedInterval;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;

public abstract class TransformIntervalBase2 extends TimeBasedInterval{

	byte transformType;

	protected Interpolation interpolation;
	float distanceX;
	float distanceY;
	Point2f start;
	Point2f end;
	
	public TransformIntervalBase2(IntervalTransformable transformable, float duration, Point2f start, Point2f end, String interpolation) {
		super(transformable, duration);
		
		this.end = new Point2f(end.x, end.y);
		if(start != null){
			this.start = new Point2f(start.x, start.y);
		}
		
		try {
			this.interpolation =  (Interpolation)Interpolation.class.getField(interpolation).get(null);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void start(){
		determineStart();
		super.start();
	}
	
	public void loop(){
		determineStart();
		super.loop();
	}
	
	public void updateMain(){
		super.updateMain();
		updateTransform();
	}
	
	private void determineStart(){
		if(start == null){
			IntervalTransformValue pos = transformable.getTransform(transformType);
			this.start = new Point2f(pos.x, pos.y);
		}
		distanceX = end.x - start.x;
		distanceY = end.y - start.y;
	}
	
	private void updateTransform(){
		float x = distanceX;
		float y = distanceY;

		float alpha = Math.min(1, currentTime / duration);
		
		x *= interpolation.apply(alpha);
		y *= interpolation.apply(alpha);
		
		transformable.setTransform(transformType, new IntervalTransformValue(start.x + x, start.y + y));
	}
	
}
