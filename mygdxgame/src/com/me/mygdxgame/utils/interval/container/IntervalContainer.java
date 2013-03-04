package com.me.mygdxgame.utils.interval.container;

import java.util.ArrayList;
import java.util.List;

import com.me.mygdxgame.utils.interval.base.IntervalBase;
import com.me.mygdxgame.utils.interval.interfaces.Interval;

public abstract class IntervalContainer extends IntervalBase{

	protected List<Interval> intervals;
	protected int index;
	
	public IntervalContainer(){
		intervals = new ArrayList<Interval>();
	}
	
	public Interval getCurrentInterval(){
		return intervals.get(index);
	}
	
	public void add(Interval interval){
		intervals.add(interval);
	}
	
	public void finish(){
		index = intervals.size() - 1;
		getCurrentInterval().finish();
		update();
	}
	
	public void reset() {
		index = 0;
		for(Interval interval : intervals){
			interval.reset();
		}
	}
	
	public boolean isFinished() {
		return index >= intervals.size();
	}

	public boolean isPlaying() {
		return super.isPlaying() && index < intervals.size();
	}

	/*
	public List<IntervalTransformable> getTransformables(){
		List<IntervalTransformable> result = new ArrayList<IntervalTransformable>();
		getTransformablesRec(result);
		return result;
	}
	
	public void getTransformablesRec(List<IntervalTransformable> result){
		for(IntervalPlayable interval : intervals){
			if(interval instanceof Interval){
				result.add(((Interval) interval).getTransformable());
			}
			else{
				getTransformablesRec(result);
			}
		}
	}*/

	public void pause(){
		super.pause();
		for(Interval interval : intervals){
			interval.pause();
		}
	}
	
	public void resume(){
		super.resume();
		for(Interval interval : intervals){
			interval.resume();
		}
	}
	
	public void stopAndRemove(){
		for(Interval interval : intervals){
			interval.stopAndRemove();
		}
		super.stopAndRemove();
	}
	
	public void stopAndRemoveLater(){
		for(Interval interval : intervals){
			interval.stopAndRemoveLater();
		}
		super.stopAndRemoveLater();
	}
	
}