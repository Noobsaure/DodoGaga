package com.me.mygdxgame.mgr;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.interfaces.IntervalPlayable;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;

public class IntervalMgr {

	private static List<IntervalPlayable> intervals = new ArrayList<IntervalPlayable>();
	private static List<IntervalPlayable> finishedIntervals = new ArrayList<IntervalPlayable>();
	private static Map<IntervalTransformable, IntervalPlayable> intervalsTransformable = new Hashtable<IntervalTransformable, IntervalPlayable>();
	
	
	public static void update(){
		for(IntervalPlayable interval : intervals){
			interval.update();
		}
		
		if(finishedIntervals.size() > 0){
			deleteFinishedIntervals();
		}
		System.out.println(intervals.size());
		System.out.println(intervalsTransformable.size());
	}
	
	private static void deleteFinishedIntervals(){
		for(IntervalPlayable interval : finishedIntervals){
			intervals.remove(interval);
			for(IntervalTransformable transformable : interval.getTransformables()){
				intervalsTransformable.remove(transformable);
			}
		}
		finishedIntervals.clear();
	}
	
	public static void deleteLater(IntervalPlayable interval){
		finishedIntervals.add(interval);
	}
	
	public static void addInterval(IntervalPlayable interval){
		for(IntervalTransformable transformable : interval.getTransformables()){
			removeTransformable(transformable);
			intervalsTransformable.put(transformable, interval);
			//intervalsTransformable.get(transformable).stopAndDelete();
			//intervalsTransformable.remove(transformable);
		}
		//deleteLater(interval);
		deleteFinishedIntervals();
		intervals.add(interval);
	}
	
	
	private static void removeTransformable(IntervalTransformable transformable){
		intervals.remove(intervalsTransformable.get(transformable));
		intervalsTransformable.remove(transformable);
	}
	
}

