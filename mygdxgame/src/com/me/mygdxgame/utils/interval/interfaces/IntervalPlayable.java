package com.me.mygdxgame.utils.interval.interfaces;



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
	public void stopAndRemoveLater();
	
	public void pause();
	public void resume();
	public void tooglePauseResume();
	
	//public List<IntervalTransformable> getTransformables();
	
	
}
