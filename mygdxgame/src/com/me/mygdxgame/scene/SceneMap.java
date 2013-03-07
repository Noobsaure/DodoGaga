package com.me.mygdxgame.scene;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Linear;
import aurelienribon.tweenengine.equations.Quart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameBattler;
import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.game.GameMoverAccessor;
import com.me.mygdxgame.ia.pathfinding.AStarPathFinder;
import com.me.mygdxgame.ia.pathfinding.Path;
import com.me.mygdxgame.ia.pathfinding.Path.Step;
import com.me.mygdxgame.ia.pathfinding.PathFinder;
import com.me.mygdxgame.ia.pathfinding.heuristics.ManhattanHeuristic;
import com.me.mygdxgame.mgr.WindowMgr;
import com.me.mygdxgame.sprite.SpritesetMap;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;

public class SceneMap extends SceneBase implements InputProcessor{

	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();
	final Vector3 highlight = new Vector3();

	private Point2f target; 

	private int currentBattlerIndex = 0;
	private GameBattler currentBattler;

	private PathFinder finder;
	private Path path;

	private TweenManager manager;

	SpritesetMap spriteset;

	public SceneMap(){
		Game.map.setup(0);
		spriteset = new SpritesetMap();
		InputMultiplexer plex = new InputMultiplexer();
		plex.addProcessor(this);
		plex.addProcessor(WindowMgr.stage);
		Gdx.input.setInputProcessor(plex);
		currentBattler = Game.map.getGameBattler(currentBattlerIndex);
		finder = new AStarPathFinder(Game.map,0,false,new ManhattanHeuristic(1));
		manager = new TweenManager();
		Tween.registerAccessor(GameMover.class, new GameMoverAccessor());
	}
	/**/
	public void updatePre(){
		super.updatePre();
		manager.update(Gdx.graphics.getDeltaTime());
	}
	/**/
	public void updateMain(){
		super.updateMain();
		Game.camera.update();
		Game.map.update();
		spriteset.update();
	}

	public void terminate() {
		super.terminate();
		//removeInputListener(this);
	}

	@Override
	public boolean keyDown(int keycode) {
		//TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		System.out.println(character);
		switch(character) {
		case 'a':
			Game.camera.zoom += 0.1;
			break;
		case 'q':
			Game.camera.zoom -= 0.1;
			break;
		}
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {

		if(button == Buttons.LEFT && path != null) {
			Step step;
			Timeline tlx = Timeline.createSequence();
			Timeline tly = Timeline.createSequence();
			for(int i=0;i<path.getLength();i++) {
				step = path.getStep(i);
				target = Game.map.heightTileToIsof(step.getX(),step.getY());
				tlx.push(Tween.to(currentBattler, GameMoverAccessor.POSITION_X, 0.25f).ease(Linear.INOUT).target(target.x));
				tly.push(Tween.to(currentBattler, GameMoverAccessor.POSITION_Y, 0.2f).ease(Quart.OUT).target(target.y-64));
				tly.push(Tween.to(currentBattler, GameMoverAccessor.POSITION_Y, 0.05f).ease(Linear.INOUT).target(target.y));
			}
			tlx.start(manager);
			tly.start(manager);
			currentBattlerIndex = (currentBattlerIndex + 1) % Game.map.getGameBattlers().size();
			currentBattler = Game.map.getGameBattlers().get(currentBattlerIndex);
		}
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		last.set(-1, -1, -1);
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT) || Gdx.input.isTouched(1)){

			Ray pickRay = Game.camera.getPickRay(Gdx.input.getX(), Gdx.input.getY());
			Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, curr);
			if(!(last.x == -1 && last.y == -1 && last.z == -1)) {
				pickRay = Game.camera.getPickRay(last.x, last.y);
				Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, delta);			
				delta.sub(curr);
				Game.camera.position.add(delta.x, delta.y, delta.z);
			}
			last.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		}
		return true;
	}

	//@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		switch(amount) {
		case 1:
			Game.camera.zoom += 0.1;
			break;
		case -1:
			Game.camera.zoom -= 0.1;
			break;
		}
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Ray pickRay = Game.camera.getPickRay(screenX, screenY);
		Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, highlight);
		Point2i currentTile = Game.map.heightIsoToTile(highlight.x - Cst.TILE_HW, -highlight.y - Cst.TILE_HH);
		spriteset.setHighlightedTile(currentTile);
		if(currentBattler != null) {
			if(currentBattler.isTileReachable(currentTile)) {
				finder.setMaxSearchDistance(currentBattler.getMovementPoints());
				int sx = currentBattler.getTilePosition().x;
				int sy = currentBattler.getTilePosition().y;
				int tx = currentTile.x;
				int ty = currentTile.y;
				path = finder.findPath(currentBattler, sx, sy, tx, ty);
			} else {
				path = null;
			}
			spriteset.setPath(path);
		}
		return true;
	}
}
