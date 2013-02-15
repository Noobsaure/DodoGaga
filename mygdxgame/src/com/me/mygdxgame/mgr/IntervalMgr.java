package com.me.mygdxgame.mgr;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.interfaces.AbstractInterval;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;

public class IntervalMgr {

	private static List<AbstractInterval> intervals = new ArrayList<AbstractInterval>();
	private static List<AbstractInterval> finishedIntervals = new ArrayList<AbstractInterval>();
	private static List<AbstractInterval> waitingForStartIntervals = new ArrayList<AbstractInterval>();
	//private static Map<IntervalTransformable, IntervalPlayable> intervalsTransformable = new Hashtable<IntervalTransformable, IntervalPlayable>();
	
	
	public static void update(){
		//System.out.println(intervals.size());
		for(AbstractInterval interval : intervals){
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
		for(AbstractInterval interval : finishedIntervals){
			intervals.remove(interval);
			//for(IntervalTransformable transformable : interval.getTransformables()){
			//	intervalsTransformable.remove(transformable);
			//}
		}
		finishedIntervals.clear();
	}
	
	private static void addIntervals(){
		for(AbstractInterval interval : waitingForStartIntervals){
			intervals.add(interval);
		}
		waitingForStartIntervals.clear();
	}
	
	public static void removeLater(AbstractInterval interval){
		finishedIntervals.add(interval);
	}
	
	public static void remove(AbstractInterval interval){
		intervals.remove(interval);
	}
	
	public static void addInterval(AbstractInterval interval){
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

