package com.me.mygdxgame.utils.interval;

import java.util.ArrayList;
import java.util.List;

import com.me.mygdxgame.mgr.IntervalMgr;

public class Sequence extends TransformableContainer implements IntervalPlayable{

	private List<IntervalPlayable> intervals;
	public int index;
	
	public Sequence(){
		intervals = new ArrayList<IntervalPlayable>();
	}
	
	public void add(IntervalPlayable interval){
		intervals.add(interval);
	}
	
	public void reset() {
		index = 0;
		for(IntervalPlayable interval : intervals){
			interval.reset();
		}
	}
	
	public void start(){
		reset();
		state = IntervalBase.State.PLAYING_ONCE;
		launch();
	}
	
	public void loop(){
		index = 0;
		state = IntervalBase.State.LOOPING;
		launch();
	}
	
	private void launch(){
		IntervalMgr.removeTransformables(getTransformables());
		IntervalMgr.addInterval(this);
	}
	
	public void update(){
		if(state == IntervalBase.State.DELETED || state == IntervalBase.State.PAUSED){
			return;
		}
		
		IntervalPlayable interval = intervals.get(index);
		interval.update();
		
		if(interval.isFinished()){
			index += 1;
		}
		
		if(isFinished()){
			if(state == IntervalBase.State.PLAYING_ONCE){
				IntervalMgr.deleteLater(this);
			}
			else if(state == IntervalBase.State.LOOPING){
				reset();
			}	
		}
		
		
	}

	public boolean isFinished() {
		return index >= intervals.size();
	}

	public boolean isPlaying() {
		return index < intervals.size();
	}
	
	public void stopAndDelete(){
		state = IntervalBase.State.DELETED;
		IntervalMgr.deleteLater(this);
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


	
}
