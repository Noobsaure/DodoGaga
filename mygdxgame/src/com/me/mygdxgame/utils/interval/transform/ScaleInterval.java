package com.me.mygdxgame.utils.interval.transform;

import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;

public class ScaleInterval extends TransformIntervalBase2{
	
	public ScaleInterval(IntervalTransformable transformable, float duration, Point2f end, String xInterpolation, String yInterpolation) {
		this(transformable, duration, end, xInterpolation, yInterpolation, null);
	}
	
	public ScaleInterval(IntervalTransformable transformable, float duration, Point2f end, String xInterpolation, String yInterpolation, Point2f start) {
		super(transformable, duration, end, xInterpolation, yInterpolation, start);
		transformType = Interval.TransformType.SCALE;
	}

	
}
