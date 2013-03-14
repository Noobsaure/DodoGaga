package com.me.mygdxgame.game;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

public class MovementCompleteCallback implements TweenCallback{
	
	private static int nbcb = 0;
	
	@Override
	public void onEvent(int type, BaseTween<?> source) {
		GameBattler battler = (GameBattler)source.getUserData();
		nbcb++;
		System.out.println("callback n°"+nbcb);
		battler.initiateNextMovement();
	}
}
