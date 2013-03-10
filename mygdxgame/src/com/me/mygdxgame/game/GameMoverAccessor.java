package com.me.mygdxgame.game;

import aurelienribon.tweenengine.TweenAccessor;

public class GameMoverAccessor implements TweenAccessor<GameMover> {

	public static final int ADD_OFFSETS = 0;
	public static final int ADD_HEIGHT = 1;
	public static final int ROTATION = 2;
	public static final int ACCUMULATED_OFFSETS = 3;

	@Override
	public int getValues(GameMover target, int tweenType, float[] returnValues) {
		switch(tweenType) {
		case ADD_OFFSETS:
			returnValues[0] = target.getOffsetX();
			returnValues[1] = target.getOffsetY();
			return 2;
		case ROTATION:
			returnValues[0] = target.getRotation();
			return 1;
		case ACCUMULATED_OFFSETS:
			returnValues[0] = target.getAccumulatedOffsetX();
			returnValues[1] = target.getAccumulatedOffsetY();
			return 2;
		}
		return 0;
	}

	@Override
	public void setValues(GameMover target, int tweenType, float[] newValues) {
		switch(tweenType) {
		case ROTATION:
			target.setRotation(newValues[0]);
			break;
		case ADD_OFFSETS:
			target.addOffsets(newValues[0]-target.getAccumulatedOffsetX(), newValues[1]-target.getAccumulatedOffsetY());
			break;
		case ACCUMULATED_OFFSETS:
			target.setAccumulatedOffsets(newValues[0], newValues[1]);
			break;
		}
	}
}
