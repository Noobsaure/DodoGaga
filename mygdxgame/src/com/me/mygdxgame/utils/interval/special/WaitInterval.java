package com.me.mygdxgame.utils.interval.special;

import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;

public class WaitInterval extends Interval{

	public WaitInterval(float duration){
		super(null, duration);
	}

	@Override
	public void determineStartKind() {
	}

	@Override
	public void applyTransform() {
	}
	
}
