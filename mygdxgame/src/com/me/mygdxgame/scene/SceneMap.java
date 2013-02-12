package com.me.mygdxgame.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameEvent;
import com.me.mygdxgame.game.GameMap;
import com.me.mygdxgame.mgr.SceneMgr;
import com.me.mygdxgame.sprite.SpritesetMap;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2i;
import com.me.mygdxgame.utils.interval.Interval;

public class SceneMap extends SceneBase implements InputProcessor{
	
	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();
	final Vector3 highlight = new Vector3();
	
	SpritesetMap spriteset;
	
	public SceneMap(){
		Game.map.setup(Cst.MAP_SIZE);
		spriteset = new SpritesetMap();
		
		Gdx.input.setInputProcessor(this); //enable event handling
	}
	
	public void update(){
		Game.cam.update();
		Game.map.update();
		spriteset.update();
		super.update();
	}

	public void terminate() {
		super.terminate();
		//removeInputListener(this);
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.P){
			//Interval interval = new Interval(new GameMovable(0), 1, new Vector2(0,0), new Vector2(2,2), "linear");
			//interval.start();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		if(Gdx.input.isKeyPressed(Keys.A)){
			Game.cam.zoom += 0.1;
		}
		else if(Gdx.input.isKeyPressed(Keys.Q)){
			Game.cam.zoom -= 0.1;
		}
		
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		
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

			Ray pickRay = Game.cam.getPickRay(Gdx.input.getX(), Gdx.input.getY());
			Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, curr);
	 
			if(!(last.x == -1 && last.y == -1 && last.z == -1)) {
				pickRay = Game.cam.getPickRay(last.x, last.y);
				Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, delta);			
				delta.sub(curr);
				Game.cam.position.add(delta.x, delta.y, delta.z);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Ray pickRay = Game.cam.getPickRay(screenX, screenY);
		Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, highlight);
		spriteset.highlightTile(GameMap.isoToTile(highlight.x, highlight.y));
		return true;
	}
	
}
