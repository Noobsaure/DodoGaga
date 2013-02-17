package com.me.mygdxgame.scene;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameBattler;
import com.me.mygdxgame.game.GameEvent;
import com.me.mygdxgame.game.GameMap;
import com.me.mygdxgame.mgr.IntervalMgr;
import com.me.mygdxgame.mgr.SceneMgr;
import com.me.mygdxgame.mgr.WindowMgr;
import com.me.mygdxgame.sprite.SpritesetMap;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;
import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.Sequence;
import com.me.mygdxgame.utils.interval.special.WaitInterval;
import com.me.mygdxgame.utils.interval.transform.PosInterval;

public class SceneMap extends SceneBase implements InputProcessor{
	
	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();
	final Vector3 highlight = new Vector3();
	
	SpritesetMap spriteset;
	
	Interval intervalTest;
	Sequence sequenceTest;
	
	public SceneMap(){
		Game.map.setup(0);
		spriteset = new SpritesetMap();
		/*
		intervalTest = new PosInterval(Game.map.movableBattlerTest, 0.5f, new Point2f(200,200), "linear", "linear");
		sequenceTest = new Sequence();
		sequenceTest.add(new PosInterval(Game.map.movableBattlerTest, 0.5f, new Point2f(200,400), "circleIn", "circleIn"));
		sequenceTest.add(new PosInterval(Game.map.movableBattlerTest, 0.5f, new Point2f(800,400), "swingIn", "swingIn"));
		sequenceTest.add(new PosInterval(Game.map.movableBattlerTest, 0.5f, new Point2f(800,200), "swing", "swing"));
		sequenceTest.add(new PosInterval(Game.map.movableBattlerTest, 0.5f, new Point2f(200,200), "swingOut", "swingOut"));
		*/
		InputMultiplexer plex = new InputMultiplexer();
		plex.addProcessor(this);
		plex.addProcessor(WindowMgr.stage);
		Gdx.input.setInputProcessor(plex);
		//Gdx.input.setInputProcessor(this); //enable event handling
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
		if(keycode == Keys.S){
			Random rand = new Random();
			for(int i=0; i<Game.map.movableBattlerTest.size(); i++){
				GameBattler battler = Game.map.movableBattlerTest.get(i);
				Point2f startPos = battler.getPosition();
				Sequence seq = new Sequence();
				seq.add(new PosInterval(battler, 0.5f, null, new Point2f(200+rand.nextInt(600),200+rand.nextInt(600)), "linear", "linear"));
				seq.add(new WaitInterval(1));
				seq.add(new PosInterval(battler, 0.5f, null, new Point2f(200+rand.nextInt(600),200+rand.nextInt(600)), "linear", "linear"));
				seq.add(new PosInterval(battler, 0.5f, null, new Point2f(200+rand.nextInt(600),200+rand.nextInt(600)), "linear", "linear"));
				seq.add(new PosInterval(battler, 0.5f, null, startPos, "linear", "linear"));
				seq.loop();
			}

			//sequenceTest.start();
			/*
			Random rand = new Random();
			
			Point2i tile = new Point2i(rand.nextInt(5), rand.nextInt(10));
			Point2i cell = new Point2i(rand.nextInt(Cst.NB_CELL), rand.nextInt(Cst.NB_CELL));
			
			Game.map.movableBattlerTest.startIntervalToTile(tile, cell);*/
			//Game.map.movableBattlerTest.setRealPosition(new Point2f(900,300));
			//Interval interval = new Interval(Game.map.movableBattlerTest, 1, new Vector2(600,300), new Vector2(1000,1000), "pow5Out");
			//interval.start();
		}
		else if(keycode == Keys.P){
			sequenceTest.tooglePauseResume();
			//Game.map.movableBattlerTest.setRealPosition(new Point2f(900,300));
			//Interval interval = new Interval(Game.map.movableBattlerTest, 1, new Vector2(600,300), new Vector2(1000,1000), "pow5Out");
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
			Game.camera.zoom += 0.1;
		}
		else if(Gdx.input.isKeyPressed(Keys.Q)){
			Game.camera.zoom -= 0.1;
		}
		
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		
		if(button == Buttons.LEFT) {
			
			Ray pickRay = Game.camera.getPickRay(x, y);
			Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, highlight);
			Point2i pos = GameMap.isoToTile(highlight.x, highlight.y);
			float tx = x - (Cst.TILE_W * pos.x + (pos.y % 2) * Cst.TILE_HW) - Cst.TILE_HW;
			float ty = y - Cst.TILE_HH * pos.y;
			
			int i = (int)(0.5 * (ty/Cst.CELL_HH + tx/Cst.CELL_HW));
			int j =	(int)(0.5 * (ty/Cst.CELL_HH - tx/Cst.CELL_HW));
			System.out.println(i+" ; "+j);
			Point2i tile = new Point2i(pos.x, pos.y);
			Point2i cell = new Point2i(i, j);
			System.out.println("Deplacement en Tile: " + tile + "   Cell: " + cell);
			Game.map.movableBattlerTest.get(0).startIntervalToTile(tile, cell);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Ray pickRay = Game.camera.getPickRay(screenX, screenY);
		Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, highlight);
		spriteset.highlightTile(GameMap.isoToTile(highlight.x, highlight.y));
		return true;
	}
	
}
