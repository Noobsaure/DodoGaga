package com.me.mygdxgame.utils.interval;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.mgr.IntervalMgr;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.interval.interfaces.AbstractInterval;
import com.me.mygdxgame.utils.interval.interfaces.IntervalPlayable;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;

/*
	static private final String[] interpolators = new String[] {"bounce", "bounceIn", "bounceOut", "circle", "circleIn",
		"circleOut", "elastic", "elasticIn", "elasticOut", "exp10", "exp10In", "exp10Out", "exp5", "exp5In", "exp5Out", "fade",
		"linear", "pow2", "pow2In", "pow2Out", "pow3", "pow3In", "pow3Out", "pow4", "pow4In", "pow4Out", "pow5", "pow5In",
		"pow5Out", "sine", "sineIn", "sineOut", "swing", "swingIn", "swingOut"};
 */
public abstract class Interval extends IntervalBase implements IntervalPlayable{
	
	public class State{
		public final static byte STOPPED = 0;
		public final static byte PLAYING_ONCE = 1;
		public final static byte LOOPING = 2;
		public final static byte PAUSED = 3;
	}
	
	public class TransformType{
		public final static byte POS = 0;
		public final static byte ROT = 1;
		public final static byte SCALE = 2;
	}
	
	protected float duration;
	protected float currentTime;
	protected IntervalTransformable transformable;
	
	public Interval(IntervalTransformable transformable, float duration){
		super();
		this.transformable = transformable;
		this.duration = duration;
		currentTime = 0;		
	}
	
	public void start(){
		determineStartKind();
		super.start();
	}
	
	public void loop(){
		determineStartKind();
		super.loop();
	}
	
	public void reset(){
		currentTime = 0;
	}
	
	public void finish(){
		currentTime = duration;
		update();
	}
	
	public void updateMain(){

		currentTime += Gdx.graphics.getDeltaTime();
		
		applyTransform();
	}
	
	public IntervalTransformable getTransformable(){
		return transformable;
	}
	
	public boolean isPlaying(){
		return super.isPlaying() && currentTime < duration;
	}

	public boolean isFinished(){
		return currentTime >= duration;
	}

	/*
	public List<IntervalTransformable> getTransformables() {
		List<IntervalTransformable> transformables = new ArrayList<IntervalTransformable>();
		transformables.add(transformable);
		return transformables;
	}*/
	

}
