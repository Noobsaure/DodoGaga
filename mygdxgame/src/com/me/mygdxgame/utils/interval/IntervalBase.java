package com.me.mygdxgame.utils.interval;

import com.me.mygdxgame.mgr.IntervalMgr;
import com.me.mygdxgame.utils.interval.interfaces.AbstractInterval;


public abstract class IntervalBase implements AbstractInterval{
	
	protected int state;
	protected int memoState;
	
	public IntervalBase(){
		state = Interval.State.STOPPED;
		memoState = state;
	}
	
	public int getState(){
		return state;
	}
	
	public void update(){
		if(state == Interval.State.STOPPED || state == Interval.State.PAUSED){
			return;
		}
		updateMain();
		updatePost();
	}
	
	public void updatePost(){
		if(isFinished()){
			if(state == Interval.State.PLAYING_ONCE){
				stopAndRemoveLater();
				//IntervalMgr.deleteLater(this);
			}
			else if(state == Interval.State.LOOPING){
				reset();
			}	
		}
	}
	
	public void start(){
		stopAndRemove();
		//resume();
		reset();
		state = Interval.State.PLAYING_ONCE;
		launch();
	}
	
	public void loop(){
		stopAndRemove();
		//resume();
		reset();
		state = Interval.State.LOOPING;
		launch();
	}
	
	private void launch(){
		//IntervalMgr.removeTransformables(getTransformables());
		IntervalMgr.addInterval(this);
	}

	public void pause(){
		memoState = state;
		state = Interval.State.PAUSED;
	}
	
	public void resume(){
		state = memoState;
	}
	
	public void tooglePauseResume(){
		if(state == Interval.State.PAUSED){
			resume();
		}
		else{
			pause();
		}
	}
	
	public void stopAndRemove(){
		state = Interval.State.STOPPED;
		IntervalMgr.remove(this);
	}
	
	public void stopAndRemoveLater(){
		state = Interval.State.STOPPED;
		IntervalMgr.removeLater(this);
	}
	
	public boolean isPlaying(){
		return (state == Interval.State.PLAYING_ONCE || state == Interval.State.LOOPING);
	}
	
	public boolean isStopped(){
		return state == Interval.State.STOPPED;
	}
	
}

    
