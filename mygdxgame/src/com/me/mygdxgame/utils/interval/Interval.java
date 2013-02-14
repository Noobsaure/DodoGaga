package com.me.mygdxgame.utils.interval;

import java.util.ArrayList;
import java.util.List;

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
public class Interval extends TransformableContainer implements IntervalPlayable{

	private float duration;
	private float currentTime;
	
	private Interpolation interpolation;
	Vector2 temp;
	Vector2 start;
	Vector2 end;
	
	public Interval(IntervalTransformable transformable, float duration, Point2f start, Point2f end, String interpolation){
		this(transformable, duration, new Vector2(start.x, start.y), new Vector2(end.x, end.y), interpolation);
	}
	
	public Interval(IntervalTransformable transformable, float duration, Vector2 start, Vector2 end, String interpolation){
		super();
		transformables.add(transformable);
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
	
	public void start(){
		reset();
		state = IntervalBase.State.PLAYING_ONCE;
		launch();
	}
	
	public void loop(){
		reset();
		state = IntervalBase.State.LOOPING;
		launch();
	}
	
	private void launch(){
		IntervalMgr.removeTransformables(getTransformables());
		IntervalMgr.addInterval(this);
	}
	
	public void finish(){
		currentTime = duration;
	}
	
	public void stopAndDelete(){
		state = IntervalBase.State.DELETED;
		IntervalMgr.deleteLater(this);
	}
	
	public void pause(){
		memoState = state;
		state = IntervalBase.State.PAUSED;
	}
	
	public void resume(){
		state = memoState;
	}
	
	public void tooglePauseResume(){
		if(state == IntervalBase.State.PAUSED){
			resume();
		}
		else{
			pause();
		}
	}
	
	public void update(){
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

		if(isFinished()){
			if(state == IntervalBase.State.PLAYING_ONCE){
				IntervalMgr.deleteLater(this);
			}
			else if(state == IntervalBase.State.LOOPING){
				reset();
			}	
		}
		
	}
	
	public IntervalTransformable getTransformable(){
		return transformables.get(0);
	}
	
	public boolean isPlaying(){
		return currentTime < duration;
	}

	public boolean isFinished(){
		return currentTime >= duration;
	}
	
}
