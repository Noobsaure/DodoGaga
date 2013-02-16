package com.me.mygdxgame.utils.interval.transform;

import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;

public class ScaleInterval extends TransformIntervalBase2{
	
	public ScaleInterval(IntervalTransformable transformable, float duration, Point2f start, Point2f end, String xInterpolation, String yInterpolation) {
		super(transformable, duration, start, end, xInterpolation, yInterpolation);
		transformType = Interval.TransformType.SCALE;
	}

	
}
