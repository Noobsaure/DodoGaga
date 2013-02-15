package com.me.mygdxgame.utils.interval.interfaces;

import java.util.List;


public interface IntervalPlayable{

	public boolean isPlaying();
	public boolean isFinished();
	
	public void update();
	public void updateMain();
	public void updatePost();
	
	public void reset();
	public void start();
	public void loop();
	public void finish();
	
	public void stopAndDelete();
	
	public void pause();
	public void resume();
	public void tooglePauseResume();
	
	public List<IntervalTransformable> getTransformables();
	
	
}
