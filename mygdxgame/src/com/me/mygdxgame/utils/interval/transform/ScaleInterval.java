package com.me.mygdxgame.utils.interval.transform;

import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.interval.base.TimeBasedInterval;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;

public class ScaleInterval extends TransformIntervalBase2{
	
	public ScaleInterval(IntervalTransformable transformable, float duration, Point2f start, Point2f end, String interpolation) {
		super(transformable, duration, start, end, interpolation);
		transformType = TimeBasedInterval.TYPE.SCALE;
	}

	
}
