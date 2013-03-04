package com.me.mygdxgame.game;

import aurelienribon.tweenengine.TweenAccessor;

public class GameMoverAccessor implements TweenAccessor<GameMover> {

	public static final int POSITION_X = 1;
	public static final int POSITION_Y = 2;
	public static final int POSITION_XY = 3;
	public static final int ROTATION = 4;

	@Override
	public int getValues(GameMover target, int tweenType, float[] returnValues) {
		switch(tweenType) {
		case POSITION_X:
			returnValues[0] = target.getPosition().x;
			return 1;
		case POSITION_Y:
			returnValues[0] = target.getPosition().y;
			return 1;
		case POSITION_XY:
			returnValues[0] = target.getPosition().x;
			returnValues[1] = target.getPosition().y;
			return 2;
		case ROTATION:
			returnValues[0] = target.getRotation();
			return 1;
		}
		return 0;
	}

	@Override
	public void setValues(GameMover target, int tweenType, float[] newValues) {
		switch(tweenType) {
		case POSITION_X:
			target.setPositionX(newValues[0]);
			break;
		case POSITION_Y:
			target.setPositionY(newValues[0]);
			break;
		case POSITION_XY:
			target.setPositionX(newValues[0]);
			target.setPositionY(newValues[1]);
			break;
		case ROTATION:
			target.setRotation(newValues[0]);
			break;
		}
	}
}
