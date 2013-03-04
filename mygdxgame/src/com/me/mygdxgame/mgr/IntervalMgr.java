package com.me.mygdxgame.mgr;

import java.util.ArrayList;
import java.util.List;

import com.me.mygdxgame.utils.interval.interfaces.AbstractInterval;

public class IntervalMgr {

	private static List<AbstractInterval> intervals = new ArrayList<AbstractInterval>();
	private static List<AbstractInterval> finishedIntervals = new ArrayList<AbstractInterval>();
	private static List<AbstractInterval> waitingForStartIntervals = new ArrayList<AbstractInterval>();
	
	public static void update(){
		for(AbstractInterval interval : intervals){
			interval.update();
		}
		
		if(waitingForStartIntervals.size() > 0){
			addIntervals();
		}
		if(finishedIntervals.size() > 0){
			removeFinishedIntervals();
		}
	}
	
	private static void removeFinishedIntervals(){
		for(AbstractInterval interval : finishedIntervals){
			intervals.remove(interval);
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
	}
}

