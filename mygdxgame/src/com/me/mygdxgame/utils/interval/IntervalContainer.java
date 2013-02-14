package com.me.mygdxgame.utils.interval;

import java.util.ArrayList;
import java.util.List;

import com.me.mygdxgame.utils.interval.interfaces.IntervalPlayable;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;

public abstract class IntervalContainer extends TransformableContainer{

	protected List<IntervalPlayable> intervals;
	protected int index;
	
	public IntervalContainer(){
		intervals = new ArrayList<IntervalPlayable>();
	}
	
	public IntervalPlayable getCurrentInterval(){
		return intervals.get(index);
	}
	
	public void add(IntervalPlayable interval){
		intervals.add(interval);
	}
	
	public void finish(){
		index = intervals.size() - 1;
		getCurrentInterval().finish();
		update();
	}
	
	public void reset() {
		index = 0;
		for(IntervalPlayable interval : intervals){
			interval.reset();
		}
	}
	
	public boolean isFinished() {
		return index >= intervals.size();
	}

	public boolean isPlaying() {
		return index < intervals.size();
	}
	
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
	}
	
}
