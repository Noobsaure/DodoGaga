package com.me.mygdxgame.utils.interval.interfaces;

import com.me.mygdxgame.utils.interval.transform.IntervalTransformValue;

public interface IntervalTransformable {

	public void setTransform(int type, IntervalTransformValue value);
	public IntervalTransformValue getTransform(int type); 
	
}
