package com.me.mygdxgame.utils.interval;


import com.me.mygdxgame.utils.interval.interfaces.IntervalPlayable;

public class Sequence extends IntervalContainer{

	
	public void updateMain(){
		//System.out.println(index);
		IntervalPlayable interval = getCurrentInterval();
		

		//interval.update();
		
		if(interval.isFinished()){
			index += 1;
		}
		//System.out.println(getCurrentInterval().getState());
		if(!isFinished() && getCurrentInterval().isStopped()){
			getCurrentInterval().start();
		}
	}
	
}
