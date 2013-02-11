package com.me.mygdxgame.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GameMap {

	public static final Plane xyPlane = new Plane(new Vector3(0, 0, 1), 0);
	
	public int[] tilemapSize;
	public final int[] TILE_SIZE = {256, 128};
	public int TILE_WALL_HEIGHT = 64;
	
	private Map<Vector2, List<GameEvent>> events;
	
	public void setup(int[] tilemapSize){
		this.tilemapSize = new int[2];
		this.tilemapSize[0] = tilemapSize[0];
		this.tilemapSize[1] = tilemapSize[1];
		setupEvents();
	}
	
	public void setupEvents(){
		Random rand = new Random();
		
		for(int i=0; i<10; i++){
			GameEvent event = new GameEvent();
		}
	}
	
	public void convertToScreen(Vector2 point){
		float x = point.x / TILE_SIZE[0];
		float y = point.y / TILE_SIZE[1];
		point.x = - (y * TILE_SIZE[0]/2) + (x * TILE_SIZE[0]/2); // - (TILE_SIZE[0]/2);
		point.y = (y * TILE_SIZE[1]/2) + (x * TILE_SIZE[1]/2);
	}
	
	/*
	private void checkTileTouched() {
		Ray pickRay = cam.getPickRay(Gdx.input.getX(), Gdx.input.getY());
		Intersector.intersectRayPlane(pickRay, xzPlane, intersection);
		int mX = (int)intersection.x;
		int mY = (int)intersection.y;
		Vector2 point = new Vector2();
		int x = (int) (mX);// + (Gdx.graphics.getWidth()/2));
		int y = (int) (mY);// + (Gdx.graphics.getHeight()/2));
		point.x = (y + x/2)/TILE_HEIGHT;
		point.y = (y - x/2)/TILE_HEIGHT;
		//System.out.println(cam.position.x + " " + cam.position.y);
		System.out.println(point.x + " " + point.y);
	}*/
	
	public void refreshEventPosition(int tileX, int tileY, GameEvent ev){
		List<GameEvent> evs;
		
		evs = eventsAt(ev.getTilePosition());
		evs.remove(ev);
		if(evs.size() == 0){
			events.remove(new Vector2(tileX, tileY));
		}
		
		evs = eventsAt(tileX, tileY);
		if(evs == null){
			evs = new ArrayList<GameEvent>();
			events.put(new Vector2(tileX, tileY), evs);
		}
		evs.add(ev);
	}
	
	public List<GameEvent> eventsAt(int tileX, int tileY){
		return events.get(new Vector2(tileX, tileY));
	}
	
	public List<GameEvent> eventsAt(Vector2 tilePosition){
		return eventsAt((int)tilePosition.x, (int)tilePosition.y);
	}
	
}
