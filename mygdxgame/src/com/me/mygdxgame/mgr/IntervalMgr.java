package com.me.mygdxgame.mgr;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.interfaces.IntervalPlayable;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;

public class IntervalMgr {

	private static List<IntervalPlayable> intervals = new ArrayList<IntervalPlayable>();
	private static List<IntervalPlayable> finishedIntervals = new ArrayList<IntervalPlayable>();
	private static List<IntervalPlayable> waitingForStartIntervals = new ArrayList<IntervalPlayable>();
	//private static Map<IntervalTransformable, IntervalPlayable> intervalsTransformable = new Hashtable<IntervalTransformable, IntervalPlayable>();
	
	
	public static void update(){
		//System.out.println(intervals.size());
		for(IntervalPlayable interval : intervals){
			interval.update();
		}
		
		if(waitingForStartIntervals.size() > 0){
			addIntervals();
		}
		if(finishedIntervals.size() > 0){
			removeFinishedIntervals();
		}
		//System.out.println(intervals.size());
	}
	
	private static void removeFinishedIntervals(){
		for(IntervalPlayable interval : finishedIntervals){
			intervals.remove(interval);
			//for(IntervalTransformable transformable : interval.getTransformables()){
			//	intervalsTransformable.remove(transformable);
			//}
		}
		finishedIntervals.clear();
	}
	
	private static void addIntervals(){
		for(IntervalPlayable interval : waitingForStartIntervals){
			intervals.add(interval);
		}
		waitingForStartIntervals.clear();
	}
	
	public static void removeLater(IntervalPlayable interval){
		finishedIntervals.add(interval);
	}
	
	public static void remove(IntervalPlayable interval){
		intervals.remove(interval);
	}
	
	public static void addInterval(IntervalPlayable interval){
		waitingForStartIntervals.add(interval);
		/*
		for(IntervalTransformable transformable : interval.getTransformables()){
			
			if(intervalsTransformable.get(transformable) != null){
				intervalsTransformable.get(transformable).stopAndDelete();
			}
			
			removeTransformable(transformable);
			intervalsTransformable.put(transformable, interval);
			//intervalsTransformable.get(transformable).stopAndDelete();
			//intervalsTransformable.remove(transformable);
		}*/
		//deleteLater(interval);
		//deleteFinishedIntervals();
		//intervals.add(interval);
	}
	
	/*
	private static void removeTransformable(IntervalTransformable transformable){
		intervals.remove(intervalsTransformable.get(transformable));
		intervalsTransformable.remove(transformable);
	}*/
	
}

