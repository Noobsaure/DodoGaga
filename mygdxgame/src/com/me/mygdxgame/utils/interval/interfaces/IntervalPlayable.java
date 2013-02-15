package com.me.mygdxgame.utils.interval.interfaces;

import java.util.List;

import com.me.mygdxgame.utils.interval.IntervalBase;


public interface IntervalPlayable{

	public boolean isPlaying();
	public boolean isFinished();
	public boolean isStopped();
	
	public int getState();
	
	public void update();
	public void updateMain();
	public void updatePost();
	
	public void reset();
	public void start();
	public void loop();
	public void finish();
	
	public void stopAndRemove();
	public void stopAndDeleteLater();
	
	public void pause();
	public void resume();
	public void tooglePauseResume();
	
	//public List<IntervalTransformable> getTransformables();
	
	
}
