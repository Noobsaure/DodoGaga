package com.me.mygdxgame.utils.interval.transform;

import com.badlogic.gdx.math.Interpolation;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.IntervalTransformValue;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;

public abstract class TransformIntervalBase2 extends Interval{

	byte transformType;

	protected Interpolation xInterpolation;
	protected Interpolation yInterpolation;
	float distanceX;
	float distanceY;
	Point2f start;
	Point2f end;
	
	public TransformIntervalBase2(IntervalTransformable transformable, float duration, Point2f start, Point2f end, String xInterpolation, String yInterpolation) {
		super(transformable, duration);
		
		this.end = new Point2f(end.x, end.y);
		if(start != null){
			this.start = new Point2f(start.x, start.y);
		}
		
		try {
			this.xInterpolation =  (Interpolation)Interpolation.class.getField(xInterpolation).get(null);
			this.yInterpolation =  (Interpolation)Interpolation.class.getField(yInterpolation).get(null);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void determineStartKind(){
		if(start == null){
			IntervalTransformValue pos = transformable.getTransform(transformType);
			this.start = new Point2f(pos.x, pos.y);
		}
		distanceX = end.x - start.x;
		distanceY = end.y - start.y;
	}
	
	public void applyTransform(){
		float x = distanceX;
		float y = distanceY;

		float alpha = Math.min(1, currentTime / duration);
		
		x *= xInterpolation.apply(alpha);
		y *= yInterpolation.apply(alpha);
		
		transformable.setTransform(transformType, new IntervalTransformValue(start.x + x, start.y + y));
	}
	
}
