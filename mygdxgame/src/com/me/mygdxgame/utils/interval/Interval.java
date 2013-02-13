package com.me.mygdxgame.utils.interval;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.mgr.IntervalMgr;
import com.me.mygdxgame.utils.Point2f;

/*
	static private final String[] interpolators = new String[] {"bounce", "bounceIn", "bounceOut", "circle", "circleIn",
		"circleOut", "elastic", "elasticIn", "elasticOut", "exp10", "exp10In", "exp10Out", "exp5", "exp5In", "exp5Out", "fade",
		"linear", "pow2", "pow2In", "pow2Out", "pow3", "pow3In", "pow3Out", "pow4", "pow4In", "pow4Out", "pow5", "pow5In",
		"pow5Out", "sine", "sineIn", "sineOut", "swing", "swingIn", "swingOut"};
 */
public class Interval extends IntervalBase{

	private float duration;
	private float currentTime;
	private IntervalTransformable transformable;
	private Interpolation interpolation;
	Vector2 temp;
	Vector2 start;
	Vector2 end;
	

	public Interval(IntervalTransformable transformable, float duration, Vector2 start, Vector2 end, String interpolation){
		this.transformable = transformable;
		this.duration = duration;
		currentTime = 0;
		this.start = start;
		this.end = end;
		
		try {
			this.interpolation =  (Interpolation)Interpolation.class.getField(interpolation).get(null);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		
		temp = new Vector2();
		
	}
	
	private boolean isFinished(){
		return currentTime >= duration;
	}
	
	public void start(){
		IntervalMgr.removeTransformable(transformable);
		IntervalMgr.addInterval(this);
	}
	
	public void stop(){
		currentTime = duration;
	}
	
	public void update(){
		currentTime += Gdx.graphics.getDeltaTime();
		
		temp.set(end);
		temp.sub(start);
		
		float alpha = Math.min(1, currentTime / duration);
		temp.mul(alpha);
		temp.add(start);
		
		transformable.setRealPosition(new Point2f(temp.x, temp.y));
		//System.out.println(currentTime + "   ------>  " + temp);

		if(alpha == 1){
			IntervalMgr.deleteLater(this);
		}
		
	}
	
	public IntervalTransformable getTransformable(){
		return transformable;
	}
	

}
