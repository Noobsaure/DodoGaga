package com.me.mygdxgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.me.mygdxgame.mgr.WindowMgr;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;
import com.me.mygdxgame.utils.interval.transform.IntervalTransformValue;

public class WindowBase extends Window implements IntervalTransformable{

	public WindowBase(String title, Skin skin) {
		super(title, skin);
		//pad(16);
		WindowMgr.add(this);
	}

	@Override
	public void setTransform(int type, IntervalTransformValue value) {
		
	}

	@Override
	public IntervalTransformValue getTransform(int type) {
		return null;
	}
	
	public void update(){
		
	}

	@Override
	public void setTransform(int type, float x, float y) {
		// TODO Auto-generated method stub
		
	}

}
