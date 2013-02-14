package com.me.mygdxgame.utils.interval;


import com.me.mygdxgame.utils.interval.interfaces.IntervalPlayable;

public class Sequence extends IntervalContainer{

	
	public void updateMain(){
		
		IntervalPlayable interval = getCurrentInterval();
		interval.update();
		
		if(interval.isFinished()){
			index += 1;
		}
		
	}
	
}
