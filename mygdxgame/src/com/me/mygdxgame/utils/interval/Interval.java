package com.me.mygdxgame.utils.interval;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.me.mygdxgame.mgr.IntervalMgr;

public class Interval extends IntervalBase{

	private float duration;
	private float currentTime;
	private IntervalEntity entity;
	
	public Interval(IntervalEntity entity, float duration, Vector3 start, Vector3 end){
		this.entity = entity;
		this.duration = duration;
		currentTime = duration;
	}
	
	private boolean isFinished(){
		return currentTime <= 0;
	}
	
	public void start(){
	    //interval_mgr.remove_np(self.np)
	    //interval_mgr.add_interval(self)
	}
	
	public void stop(){
		currentTime = 0;
	}
	
	public void update(){
		duration -= Gdx.graphics.getDeltaTime();
		
		if(isFinished()){
			IntervalMgr.deleteLater(this);
		}
		/*
	    dt = min(globalClock.getDt(), self.current_time)
	    offset = (self.distance*dt)/self.duration
	    self.current_time -= dt
	    self.np.setPos(render, self.np.getX()+offset[0], self.np.getY()+offset[1], self.np.getZ()+offset[2])
	    if self.isFinished():
	        interval_mgr.delete_after(self)*/
	}
	
	public IntervalEntity getEntity(){
		return entity;
	}

}
