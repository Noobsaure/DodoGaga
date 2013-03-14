package com.me.mygdxgame.scene;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameBattler;
import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.game.GameMoverAccessor;
import com.me.mygdxgame.ia.pathfinding.AStarPathFinder;
import com.me.mygdxgame.ia.pathfinding.Path;
import com.me.mygdxgame.ia.pathfinding.PathFinder;
import com.me.mygdxgame.ia.pathfinding.heuristics.ManhattanHeuristic;
import com.me.mygdxgame.mgr.StageMgr;
import com.me.mygdxgame.sprite.MapRenderer;
import com.me.mygdxgame.ui.stage.StageMap;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2i;

public class SceneMap extends SceneBase implements InputProcessor{

	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();
	final Vector3 highlight = new Vector3();

	private int currentBattlerIndex = 0;
	private GameBattler currentBattler;
	private Point2i currentTile;

	private boolean combatIsOn = false;
	private boolean lockCamera = false;

	private PathFinder finder;
	private Path path;

	MapRenderer renderer;

	public SceneMap(){
		StageMgr.startStageLater(new StageMap());
		Game.map.setup(0);
		renderer = new MapRenderer();
		InputMultiplexer plex = new InputMultiplexer();
		plex.addProcessor(this);
		plex.addProcessor(StageMgr.stage);
		Gdx.input.setInputProcessor(plex);
		currentBattler = Game.map.getGameBattler(currentBattlerIndex);
		finder = new AStarPathFinder(Game.map,0,false,new ManhattanHeuristic(1));
		Tween.registerAccessor(GameMover.class, new GameMoverAccessor());
	}

	public void updatePre(){
		super.updatePre();
	}

	public void updateMain(){
		super.updateMain();
		Game.camera.update();
		Game.map.update();
		renderer.update();
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

		if(button == Buttons.LEFT) {
			if(combatIsOn) {
				if(path != null && !currentBattler.isMoving()) {
					currentBattler.subMovementPoints(path.getLength());
					currentBattler.setPath(path);
				}
			} else {
				Ray pickRay = Game.camera.getPickRay(x, y);
				Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, highlight);
				currentTile = Game.map.heightIsoToTile(highlight.x, highlight.y);
				if(currentBattler != null) {
					finder.setMaxSearchDistance(-1);
					int sx = currentBattler.getTilePosition().x;
					int sy = currentBattler.getTilePosition().y;
					int tx = currentTile.x;
					int ty = currentTile.y;
					Path path = finder.findPath(currentBattler, sx, sy, tx, ty);
					if(path != null)
					currentBattler.setPath(path);
				}
			}

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
		if(!lockCamera && (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) || Gdx.input.isTouched(1))){

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
		if(combatIsOn) {
			Ray pickRay = Game.camera.getPickRay(screenX, screenY);
			Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, highlight);
			currentTile = Game.map.heightIsoToTile(highlight.x, highlight.y);
			renderer.setHighlightedTile(currentTile);
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
				renderer.setPath(path);
			}
		}
		return true;
	}
}
