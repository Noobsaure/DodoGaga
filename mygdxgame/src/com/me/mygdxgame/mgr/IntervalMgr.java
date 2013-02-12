package com.me.mygdxgame.mgr;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.IntervalEntity;

public class IntervalMgr {

	private static List<Interval> intervals = new ArrayList<Interval>();
	private static List<Interval> finishedIntervals = new ArrayList<Interval>();
	private static Map<IntervalEntity, Interval> intervalsEntity = new Hashtable<IntervalEntity, Interval>();
	
	public static void removeEntity(){
		
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
			intervalsEntity.remove(interval.getEntity());
		}
		finishedIntervals.clear();
	}
	
	public static void deleteLater(Interval interval){
		finishedIntervals.add(interval);
	}
	
	public static void addInterval(Interval interval){
		intervalsEntity.put(interval.getEntity(), interval);
		intervals.add(interval);
	}
	
	public static void removeEntity(IntervalEntity entity){
		intervals.remove(intervalsEntity.get(entity));
		intervalsEntity.remove(entity);
	}
}

