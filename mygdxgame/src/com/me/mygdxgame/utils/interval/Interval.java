package com.me.mygdxgame.utils.interval;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.mgr.IntervalMgr;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.interval.interfaces.IntervalPlayable;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;

/*
	static private final String[] interpolators = new String[] {"bounce", "bounceIn", "bounceOut", "circle", "circleIn",
		"circleOut", "elastic", "elasticIn", "elasticOut", "exp10", "exp10In", "exp10Out", "exp5", "exp5In", "exp5Out", "fade",
		"linear", "pow2", "pow2In", "pow2Out", "pow3", "pow3In", "pow3Out", "pow4", "pow4In", "pow4Out", "pow5", "pow5In",
		"pow5Out", "sine", "sineIn", "sineOut", "swing", "swingIn", "swingOut"};
 */
public class Interval extends IntervalBase{

	private float duration;
	private float currentTime;
	
	private Interpolation xInterpolation;
	private Interpolation yInterpolation;
	float distanceX;
	float distanceY;
	//Vector2 temp;
	Point2f start;
	//Vector2 end;
	IntervalTransformable transformable;
	boolean startLocal = false;
	
	public Interval(IntervalTransformable transformable, float duration, Point2f end, String xInterpolation, String yInterpolation){
		
		//Point2f start = transformable.getPosition();
		this(transformable, duration, end, xInterpolation, yInterpolation, transformable.getPosition());
		startLocal = true;
	}
	
	public Interval(IntervalTransformable transformable, float duration, Point2f end, String xInterpolation, String yInterpolation, Point2f start){
		super();
		this.transformable = transformable;
		this.duration = duration;
		currentTime = 0;
		this.start = new Point2f(start.x, start.y);
		distanceX = end.x - start.x;
		distanceY = end.y - start.y;
		System.out.println(distanceX + " " + distanceY);
		System.out.println(start);
		
		try {
			this.xInterpolation =  (Interpolation)Interpolation.class.getField(xInterpolation).get(null);
			this.yInterpolation =  (Interpolation)Interpolation.class.getField(yInterpolation).get(null);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
	public void reset(){
		currentTime = 0;
	}
	
	public void finish(){
		currentTime = duration;
	}
	
	public void updateMain(){

		currentTime += Gdx.graphics.getDeltaTime();
		
		float x = distanceX;
		float y = distanceY;

		float alpha = Math.min(1, currentTime / duration);
		
		x *= xInterpolation.apply(alpha);
		y *= yInterpolation.apply(alpha);
		
		transformable.setPosition(start.x + x, start.y + y);
		System.out.println(transformable.getPosition());
	}
	
	public IntervalTransformable getTransformable(){
		return transformable;
	}
	
	public boolean isPlaying(){
		return currentTime < duration;
	}

	public boolean isFinished(){
		return currentTime >= duration;
	}

	@Override
	public List<IntervalTransformable> getTransformables() {
		List<IntervalTransformable> transformables = new ArrayList<IntervalTransformable>();
		transformables.add(transformable);
		return transformables;
	}
	
}
