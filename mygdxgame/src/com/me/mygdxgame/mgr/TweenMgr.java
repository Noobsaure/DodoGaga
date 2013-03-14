package com.me.mygdxgame.mgr;

import com.badlogic.gdx.Gdx;

import aurelienribon.tweenengine.TweenManager;

public class TweenMgr {
	private static TweenManager manager;
	
	public static void init() {
		manager = new TweenManager();
	}
	
	public static void update() {
		manager.update(Gdx.graphics.getDeltaTime());
	}
	
	public static TweenManager getTweenManager() {
		return manager;
	}
}
