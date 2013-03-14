package com.me.mygdxgame.game;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Linear;
import aurelienribon.tweenengine.equations.Quad;

import com.me.mygdxgame.ia.pathfinding.Path;
import com.me.mygdxgame.ia.pathfinding.Path.Step;
import com.me.mygdxgame.mgr.TweenMgr;
import com.me.mygdxgame.utils.Point2i;

public class GameBattler extends GameMover{

	private int movementPoints = 10;

	private Path path;
	private boolean hasCameraFocus;
	private boolean moving = false;
	private int xToCome;
	private int yToCome;

	public GameBattler(int id, Point2i tilePosition) {
		super(id,tilePosition);
		this.hasCameraFocus = false;
		path = new Path();
		xToCome = tilePosition.x;
		yToCome = tilePosition.y;
	}

	public GameBattler(int id, Point2i tilePosition, boolean hasCameraFocus) {
		super(id,tilePosition);
		this.hasCameraFocus = hasCameraFocus;
		path = new Path();
		xToCome = tilePosition.x;
		yToCome = tilePosition.y;
	}

	public boolean isTileReachable(Point2i tile) {
		return tile.x >= 0 && tile.y >= 0 && tile.x < Game.map.getMapSize().x && tile.y < Game.map.getMapSize().y
				&& getTilePosition().x >= 0 && getTilePosition().y >= 0
				&& getTilePosition().x < Game.map.getMapSize().x && getTilePosition().y < Game.map.getMapSize().y
				&& Game.map.getCost(this,getTilePosition(),tile) <= movementPoints;
	}

	public void initiateNextMovement() {
		if(path.getLength() <= 0) {
			moving = false;
			return;
		}
		moving = true;
		Step step = path.popStep();
		if(step.getX() == xToCome && step.getY() == yToCome && path.getLength() > 0) {
			step = path.popStep();
		}
		if(step != null) {
			xToCome = step.getX();
			yToCome = step.getY();
			float[] target = Game.map.getOffsets(getTilePosition().x,getTilePosition().y,step.getX(),step.getY(),getOffsetZ());
			Timeline tl = Timeline.createSequence();
			tl.push(Timeline.createSequence().beginParallel()
					.push(Tween.to(this, GameMoverAccessor.ADD_OFFSETS,0.4f).ease(Linear.INOUT).target(target[0],target[1]))
					.push(Timeline.createSequence()
							.push(Tween.to(this, GameMoverAccessor.ADD_HEIGHT,0.2f).ease(Quad.OUT).target(target[2]))
							.push(Tween.to(this, GameMoverAccessor.ADD_HEIGHT,0.2f).ease(Linear.INOUT).target(target[3])))
							.end()
							.push(Tween.set(this, GameMoverAccessor.ACCUMULATED_OFFSETS).target(0.f,0.f,0.f))
							.push(Tween.call(new MovementCompleteCallback()).setCallbackTriggers(TweenCallback.COMPLETE).setUserData(this)));
			tl.start(TweenMgr.getTweenManager());
		} else
			moving = false;
	}

	public void setPath(Path path) {
		this.path = new Path();
		Step step;
		for(int i=0;i<path.getLength();i++) {
			step = path.getStep(i);
			this.path.appendStep(step.getX(), step.getY());
		}
	}

	public int getMovementPoints() {return movementPoints;}
	public void setMovementPoints(int movementPoints) {this.movementPoints = movementPoints;}
	public void addMovementPoints(int movementPoints) {this.movementPoints = this.movementPoints + movementPoints;}
	public void subMovementPoints(int movementPoints) {this.movementPoints = this.movementPoints - movementPoints;}
	public boolean hasCameraFocus() {return hasCameraFocus;}
	public void setHasCameraFocus(boolean hasCameraFocus) {this.hasCameraFocus = hasCameraFocus;}
	public boolean isMoving() {return moving;}

	public void update() {
		super.update();
		if(hasCameraFocus) {
			Game.camera.position.set(getRealPositionX(), getRealPositionY()-128, 1000);
		}
		if(!moving && path != null && path.getLength() > 0) {
			initiateNextMovement();
			//System.out.println("go");
		}
	}
}
