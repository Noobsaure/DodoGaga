package com.me.mygdxgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.mygdxgame.utils.interval.base.TimeBasedInterval;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;
import com.me.mygdxgame.utils.interval.transform.IntervalTransformValue;

public class Table2 extends Table implements IntervalTransformable{

	@Override
	public void setTransform(int type, IntervalTransformValue value) {
		if(type == TimeBasedInterval.TYPE.POS){
			setPosition(value.x, value.y);
		}
		else if(type == TimeBasedInterval.TYPE.SCALE){
			System.out.println(value.x);
			setScale(value.x, value.y);
		}
	}

	@Override
	public IntervalTransformValue getTransform(int type) {
		if(type == TimeBasedInterval.TYPE.POS){
			return new IntervalTransformValue(getX(), getY());
		}
		else if(type == TimeBasedInterval.TYPE.SCALE){
			return new IntervalTransformValue(getScaleX(), getScaleY());
		}
		return null;
	}

	@Override
	public void setTransform(int type, float x, float y) {
		// TODO Auto-generated method stub
		
	}
	
}
