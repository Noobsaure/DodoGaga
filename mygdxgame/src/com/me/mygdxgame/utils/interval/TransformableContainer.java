package com.me.mygdxgame.utils.interval;

import java.util.ArrayList;
import java.util.List;

public abstract class TransformableContainer extends IntervalBase{

	protected List<IntervalTransformable> transformables;
	
	public TransformableContainer(){
		transformables = new ArrayList<IntervalTransformable>();
	}
	
	public List<IntervalTransformable> getTransformables() {
		return transformables;
	}
	
}
