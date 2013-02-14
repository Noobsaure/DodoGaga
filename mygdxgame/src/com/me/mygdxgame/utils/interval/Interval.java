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
public class Interval extends TransformableContainer{

	private float duration;
	private float currentTime;
	
	private Interpolation interpolation;
	Vector2 temp;
	Vector2 start;
	Vector2 end;
	IntervalTransformable transformable;
	
	public Interval(IntervalTransformable transformable, float duration, Point2f start, Point2f end, String interpolation){
		this(transformable, duration, new Vector2(start.x, start.y), new Vector2(end.x, end.y), interpolation);
	}
	
	public Interval(IntervalTransformable transformable, float duration, Vector2 start, Vector2 end, String interpolation){
		super();
		this.transformable = transformable;
		//transformables.add(transformable);
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
	
	public void reset(){
		currentTime = 0;
	}
	
	public void finish(){
		currentTime = duration;
	}
	
	public void updateMain(){
		if(state == IntervalBase.State.DELETED || state == IntervalBase.State.PAUSED){
			return;
		}
		
		currentTime += Gdx.graphics.getDeltaTime();
		
		temp.set(end);
		temp.sub(start);
		
		float alpha = Math.min(1, currentTime / duration);
		temp.mul(interpolation.apply(alpha));
		temp.add(start);
		
		getTransformable().setRealPosition(new Point2f(temp.x, temp.y));
		
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
