package com.me.mygdxgame.scene;

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
import com.me.mygdxgame.game.GameMap;
import com.me.mygdxgame.game.GameMapBase;
import com.me.mygdxgame.ia.pathfinding.AStarPathFinder;
import com.me.mygdxgame.ia.pathfinding.Path;
import com.me.mygdxgame.ia.pathfinding.Path.Step;
import com.me.mygdxgame.ia.pathfinding.PathFinder;
import com.me.mygdxgame.ia.pathfinding.heuristics.ManhattanHeuristic;
import com.me.mygdxgame.mgr.WindowMgr;
import com.me.mygdxgame.sprite.SpritesetMap;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2i;
import com.me.mygdxgame.utils.interval.container.Sequence;
import com.me.mygdxgame.utils.interval.transform.PosInterval;

public class SceneMap extends SceneBase implements InputProcessor{

	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();
	final Vector3 highlight = new Vector3();
	
	private int currentBattlerIndex = 0;
	private GameBattler currentBattler;
	
	private PathFinder finder;
	private Path path;
	
	SpritesetMap spriteset;

	public SceneMap(){
		Game.map.setup(0);
		spriteset = new SpritesetMap();
		InputMultiplexer plex = new InputMultiplexer();
		plex.addProcessor(this);
		plex.addProcessor(WindowMgr.stage);
		Gdx.input.setInputProcessor(plex);
		currentBattler = Game.map.getGameBattlers().get(currentBattlerIndex);
		finder = new AStarPathFinder(Game.map,0,false,new ManhattanHeuristic(1));
	}

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
		//TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {

		if(button == Buttons.LEFT && path != null) {
			Step step;
			Sequence seq = new Sequence();
			for(int i=0;i<path.getLength();i++) {
				step = path.getStep(i);
				seq.add(new PosInterval(currentBattler, 0.25f, null,GameMapBase.tileToIsof(step.getX(),step.getY()), "linear"));
			}
			seq.start();
			currentBattlerIndex = (currentBattlerIndex + 1) % Game.map.getGameBattlers().size();
			currentBattler = Game.map.getGameBattlers().get(currentBattlerIndex);
		}
		/*

			SceneMap scene = (SceneMap) SceneMgr.scene;

			Vector3 intersection = new Vector3();
			Ray pickRay = Game.cam.getPickRay(x, y);
			Intersector.intersectRayPlane(pickRay, Game.map.xyPlane, intersection);
			int mX = (int)intersection.x;
			int mY = (int)intersection.y;

			//Vector2 point = new Vector2();
			//int x = (int) (mX);// + (Gdx.graphics.getWidth()/2));
			//int y = (int) (mY);// + (Gdx.graphics.getHeight()/2));
			//point.x = (y + x/2)/TILE_HEIGHT;
			//point.y = (y - x/2)/TILE_HEIGHT;
			//System.out.println(cam.position.x + " " + cam.position.y);
			//System.out.println(point.x + " " + point.y);

			scene.spriteset.chara.setPosition(mX, mY);
		 */
		return false;
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
		return false;
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
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
			Ray pickRay = Game.camera.getPickRay(screenX, screenY);
			Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, highlight);
			Point2i currentTile = GameMap.isoToTile(highlight.x, highlight.y);
			spriteset.setHighlightedTile(currentTile);
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
			return true;
		}
}
