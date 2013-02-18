package com.me.mygdxgame.utils.interval.interfaces;



public interface Interval{

	public class State{
		public final static byte STOPPED = 0;
		public final static byte PLAYING_ONCE = 1;
		public final static byte LOOPING = 2;
		public final static byte PAUSED = 3;
	}

	public class TYPE{
		public final static byte POS = 0;
		public final static byte ROT = 1;
		public final static byte SCALE = 2;
	}

	public boolean isPlaying();
	public boolean isFinished();
	public boolean isStopped();
	public boolean isFinishedAndStart();
	
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
	
	public float getCurrentTime();
	
	//public List<IntervalTransformable> getTransformables();

	
}
