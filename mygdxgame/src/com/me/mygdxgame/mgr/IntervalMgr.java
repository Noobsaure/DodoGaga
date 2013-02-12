package com.me.mygdxgame.mgr;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.IntervalTransformable;

public class IntervalMgr {

	private static List<Interval> intervals = new ArrayList<Interval>();
	private static List<Interval> finishedIntervals = new ArrayList<Interval>();
	private static Map<IntervalTransformable, Interval> intervalsTransformable = new Hashtable<IntervalTransformable, Interval>();
	
	public static void removeTransformable(){
		
	}
	
	public static void update(){
		for(Interval interval : intervals){
			interval.update();
		}
		
		if(finishedIntervals.size() > 0){
			deleteFinishedIntervals();
		}
	}
	
	private static void deleteFinishedIntervals(){
		for(Interval interval : finishedIntervals){
			intervals.remove(interval);
			intervalsTransformable.remove(interval.getTransformable());
		}
		finishedIntervals.clear();
	}
	
	public static void deleteLater(Interval interval){
		finishedIntervals.add(interval);
	}
	
	public static void addInterval(Interval interval){
		intervalsTransformable.put(interval.getTransformable(), interval);
		intervals.add(interval);
	}
	
	public static void removeTransformable(IntervalTransformable transformable){
		intervals.remove(intervalsTransformable.get(transformable));
		intervalsTransformable.remove(transformable);
	}
}

