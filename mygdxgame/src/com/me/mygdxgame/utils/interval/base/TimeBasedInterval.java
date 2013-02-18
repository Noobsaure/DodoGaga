package com.me.mygdxgame.utils.interval.base;

import com.badlogic.gdx.Gdx;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;


public abstract class TimeBasedInterval extends IntervalBase{
	
	protected float duration;
	protected float currentTime;
	protected IntervalTransformable transformable;
	
	public TimeBasedInterval(IntervalTransformable transformable, float duration){
		super();
		this.transformable = transformable;
		this.duration = duration;
		currentTime = 0;		
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
	
	public float getCurrentTime(){
		return currentTime;
	}
}
