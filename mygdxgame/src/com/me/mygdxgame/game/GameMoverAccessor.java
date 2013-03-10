package com.me.mygdxgame.game;

import aurelienribon.tweenengine.TweenAccessor;

public class GameMoverAccessor implements TweenAccessor<GameMover> {

	public static final int ADD_OFFSETS = 0;
	public static final int ADD_HEIGHT = 1;
	public static final int ACCUMULATED_OFFSETS = 2;
	public static final int ROTATION = 3;

	@Override
	public int getValues(GameMover target, int tweenType, float[] returnValues) {
		switch(tweenType) {
		case ADD_OFFSETS:
			returnValues[0] = target.getOffsetX();
			returnValues[1] = target.getOffsetY();
			return 2;
		case ADD_HEIGHT:
			returnValues[0] = target.getOffsetZ();
			return 1;
		case ACCUMULATED_OFFSETS:
			returnValues[0] = target.getAccumulatedOffsetX();
			returnValues[1] = target.getAccumulatedOffsetY();
			returnValues[2] = target.getAccumulatedOffsetZ();
			return 3;
		case ROTATION:
			returnValues[0] = target.getRotation();
			return 1;
		}
		return 0;
	}

	@Override
	public void setValues(GameMover target, int tweenType, float[] newValues) {
		switch(tweenType) {
		case ADD_OFFSETS:
			target.addOffsets(newValues[0]-target.getAccumulatedOffsetX(), newValues[1]-target.getAccumulatedOffsetY());
			break;
		case ADD_HEIGHT:
			target.addOffsetZ(newValues[0]-target.getAccumulatedOffsetZ());
			break;
		case ACCUMULATED_OFFSETS:
			target.setAccumulatedOffsets(newValues[0], newValues[1], newValues[2]);
			break;
		case ROTATION:
			target.setRotation(newValues[0]);
			break;
		}
	}
}
