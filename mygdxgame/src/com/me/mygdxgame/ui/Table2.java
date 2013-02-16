package com.me.mygdxgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.IntervalTransformValue;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;

public class Table2 extends Table implements IntervalTransformable{

	@Override
	public void setTransform(int type, IntervalTransformValue value) {
		if(type == Interval.TransformType.POS){
			setPosition(value.x, value.y);
		}
		else if(type == Interval.TransformType.SCALE){
			System.out.println(value.x);
			setScale(value.x, value.y);
		}
	}

	@Override
	public IntervalTransformValue getTransform(int type) {
		if(type == Interval.TransformType.POS){
			return new IntervalTransformValue(getX(), getY());
		}
		else if(type == Interval.TransformType.SCALE){
			return new IntervalTransformValue(getScaleX(), getScaleY());
		}
		return null;
	}
	
}
