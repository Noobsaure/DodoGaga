package com.me.mygdxgame.utils.interval;

import com.me.mygdxgame.mgr.IntervalMgr;
import com.me.mygdxgame.utils.interval.interfaces.IntervalPlayable;


public abstract class IntervalBase implements IntervalPlayable{

	public class State{
		public final static byte JUST_CREATED = 0;
		public final static byte PLAYING_ONCE = 1;
		public final static byte LOOPING = 2;
		public final static byte PAUSED = 3;
		public final static byte DELETED = 4;
	}
	
	protected int state;
	protected int memoState;
	
	public IntervalBase(){
		state = IntervalBase.State.JUST_CREATED;
		memoState = state;
	}
	
	public void update(){
		updateMain();
		updatePost();
	}
	
	public void updatePost(){
		if(isFinished()){
			if(state == IntervalBase.State.PLAYING_ONCE){
				IntervalMgr.deleteLater(this);
			}
			else if(state == IntervalBase.State.LOOPING){
				reset();
			}	
		}
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
		//IntervalMgr.removeTransformables(getTransformables());
		IntervalMgr.addInterval(this);
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
	
	public void stopAndDelete(){
		state = IntervalBase.State.DELETED;
		IntervalMgr.deleteLater(this);
	}
	
}

    
