package com.me.mygdxgame.utils.interval;

import java.util.List;

public interface IntervalPlayable extends IntervalContainable{

	public boolean isPlaying();
	public boolean isFinished();
	public void update();
	
	public void reset();
	public void start();
	public void loop();
	
	public void stopAndDelete();
	
	public void pause();
	public void resume();
	public void tooglePauseResume();
	
	
	
}
