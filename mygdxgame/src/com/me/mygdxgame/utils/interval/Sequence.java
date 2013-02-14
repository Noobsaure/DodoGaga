package com.me.mygdxgame.utils.interval;


import com.me.mygdxgame.utils.interval.interfaces.IntervalPlayable;

public class Sequence extends IntervalContainer{

	
	public void updateMain(){
		if(state == IntervalBase.State.DELETED || state == IntervalBase.State.PAUSED){
			return;
		}
		
		IntervalPlayable interval = getCurrentInterval();
		interval.update();
		
		if(interval.isFinished()){
			index += 1;
		}
	}
	
}
