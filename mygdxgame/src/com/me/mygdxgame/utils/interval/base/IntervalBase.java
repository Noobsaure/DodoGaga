package com.me.mygdxgame.utils.interval.base;

import com.me.mygdxgame.mgr.IntervalMgr;
import com.me.mygdxgame.utils.interval.interfaces.Interval;

/*
static private final String[] interpolators = new String[] {"bounce", "bounceIn", "bounceOut", "circle", "circleIn",
	"circleOut", "elastic", "elasticIn", "elasticOut", "exp10", "exp10In", "exp10Out", "exp5", "exp5In", "exp5Out", "fade",
	"linear", "pow2", "pow2In", "pow2Out", "pow3", "pow3In", "pow3Out", "pow4", "pow4In", "pow4Out", "pow5", "pow5In",
	"pow5Out", "sine", "sineIn", "sineOut", "swing", "swingIn", "swingOut"};
*/

public abstract class IntervalBase implements Interval{
	
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
		update();
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
	
	public boolean isFinishedAndStart(){
		if(isFinished()){
			start();
			return true;
		}
		if(isStopped()){
			start();
			return false;
		}
		return false;
	}
	
}

    
