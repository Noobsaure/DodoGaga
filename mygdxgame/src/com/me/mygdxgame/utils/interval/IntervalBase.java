package com.me.mygdxgame.utils.interval;


public abstract class IntervalBase {

	protected int state;
	protected int memoState;
	
	public IntervalBase(){
		state = IntervalBase.State.JUST_CREATED;
		memoState = state;
	}
	
	public class State{
		
		public final static byte JUST_CREATED = 0;
		public final static byte PLAYING_ONCE = 1;
		public final static byte LOOPING = 2;
		public final static byte PAUSED = 3;
		public final static byte DELETED = 4;
		
	}
	
}

    
