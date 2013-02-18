package com.me.mygdxgame.utils.interval.container;


import com.me.mygdxgame.utils.interval.interfaces.Interval;

public class Sequence extends IntervalContainer{

	
	public void updateMain(){
		//System.out.println(index);
		Interval interval = getCurrentInterval();
		

		//interval.update();
		
		if(interval.isFinished()){
			index += 1;
		}
		//System.out.println(getCurrentInterval().getState());
		if(!isFinished() && getCurrentInterval().isStopped()){
			getCurrentInterval().start();
		}
	}

	@Override
	public float getCurrentTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
