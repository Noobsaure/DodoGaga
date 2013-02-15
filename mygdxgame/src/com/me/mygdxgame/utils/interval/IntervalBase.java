package com.me.mygdxgame.utils.interval;

import com.me.mygdxgame.mgr.IntervalMgr;
import com.me.mygdxgame.utils.interval.interfaces.IntervalPlayable;


public abstract class IntervalBase implements IntervalPlayable{

	public class State{
		public final static byte STOPPED = 0;
		public final static byte PLAYING_ONCE = 1;
		public final static byte LOOPING = 2;
		public final static byte PAUSED = 3;
		//public final static byte DELETED = 4;
	}
	
	protected int state;
	protected int memoState;
	
	public IntervalBase(){
		state = IntervalBase.State.STOPPED;
		memoState = state;
	}
	
	public int getState(){
		return state;
	}
	
	public void update(){
		if(state == IntervalBase.State.STOPPED || state == IntervalBase.State.PAUSED){
			return;
		}
		updateMain();
		updatePost();
	}
	
	public void updatePost(){
		if(isFinished()){
			if(state == IntervalBase.State.PLAYING_ONCE){
				stopAndDeleteLater();
				//IntervalMgr.deleteLater(this);
			}
			else if(state == IntervalBase.State.LOOPING){
				reset();
			}	
		}
	}
	
	public void start(){
		stopAndRemove();
		//resume();
		reset();
		state = IntervalBase.State.PLAYING_ONCE;
		launch();
	}
	
	public void loop(){
		stopAndRemove();
		//resume();
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
	
	public void stopAndRemove(){
		state = IntervalBase.State.STOPPED;
		IntervalMgr.remove(this);
	}
	
	public void stopAndDeleteLater(){
		state = IntervalBase.State.STOPPED;
		IntervalMgr.removeLater(this);
	}
	
	public boolean isPlaying(){
		return (state == IntervalBase.State.PLAYING_ONCE || state == IntervalBase.State.LOOPING);
	}
	
	public boolean isStopped(){
		return state == IntervalBase.State.STOPPED;
	}
	
}

    
