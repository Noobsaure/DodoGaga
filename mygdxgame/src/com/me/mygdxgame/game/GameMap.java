package com.me.mygdxgame.game;

import ia.pathfinding.Mover;
import ia.pathfinding.TileBasedMap;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;

public class GameMap implements TileBasedMap {

	private static final Plane xyPlane = new Plane(new Vector3(0, 0, 1), 0);
	private Map<String, List<GameEvent>> tileEvents;
	private List<GameEvent> events;
	private final int WIDTH;
	private final int HEIGHT;

	private boolean[][] visited;
	
	public GameMap(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
	}
	
	public void setup() {
		setupEvents();
	}
	
	public void setupEvents(){
		
		tileEvents = new Hashtable<String, List<GameEvent>>();
		events = new ArrayList<GameEvent>();
		
		Random rand = new Random();
		GameEvent event;
		Point2i tilePosition;
		Point2i innerTilePosition;	
		
		for(int i=0; i<100; i++){
			tilePosition = new Point2i(rand.nextInt(WIDTH),rand.nextInt(HEIGHT));
			innerTilePosition = new Point2i(rand.nextInt(Cst.NB_X_CELL),rand.nextInt(Cst.NB_Y_CELL));
			event = new GameEvent(rand.nextInt(2),tilePosition,innerTilePosition);
			refreshEventPosition(tilePosition.x, tilePosition.y, event);
		}
	}
	
	public void convertToScreen(Vector2 point){
		float x = point.x / Cst.TILE_W;
		float y = point.y / Cst.TILE_H;
		point.x = - (y * Cst.TILE_HW) + (x * Cst.TILE_HW);
		point.y = (y * Cst.TILE_HH) + (x * Cst.TILE_HH);
	}
	
	public void convertToScreen(Point2f point){
		float x = point.x / Cst.TILE_W;
		float y = point.y / Cst.TILE_H;
		point.x = - (y * Cst.TILE_HW) + (x * Cst.TILE_HW);
		point.y = (y * Cst.TILE_HH) + (x * Cst.TILE_HH);
	}
	
	public void convertToScreen(Point2i point){
		int x = point.x / Cst.TILE_W;
		int y = point.y / Cst.TILE_H;
		point.x = - (y * Cst.TILE_HW) + (x * Cst.TILE_HW);
		point.y = (y * Cst.TILE_HH) + (x * Cst.TILE_HH);
	}
	
	public void refreshEventPosition(int tileX, int tileY, GameEvent ev){
		List<GameEvent> evs;
		
		evs = eventsAt(ev.getTilePosition());
		if(evs != null){
			//evs.remove(ev);
			if(evs.size() == 0){
				//tileEvents.remove((new Point2i(tileX, tileY)).getHashCode());
			}
		}
		
		evs = eventsAt(tileX, tileY);
		if(evs == null){
			evs = new ArrayList<GameEvent>();
			tileEvents.put(new Point2i(tileX, tileY).getHashCode(), evs);
		}
		evs.add(ev);
	
	}
	
	public List<GameEvent> eventsAt(int tileX, int tileY){
		Point2i tilePosition = new Point2i(tileX, tileY);
		return eventsAt(tilePosition);
	}
	
	public List<GameEvent> eventsAt(Point2i tilePosition){
		//if(events.containsKey(tilePosition))
			return tileEvents.get(tilePosition.getHashCode());
		//else{
		//	return null;
		//}
	}
	
	public void update(){
		for(GameEvent event : events){
			event.update();
		}
		/*
		Collection<List<GameEvent>> eventLists = events.values();
		for(List<GameEvent> evs : eventLists){
			for(GameEvent event : evs){
				System.out.println("lu");
				event.update();
			}
		}*/
	}
	
	public void clearVisited() {
		for (int x=0;x<WIDTH;x++) {
			for (int y=0;y<HEIGHT;y++) {
				visited[x][y] = false;
			}
		}
	}
	
	public int getHeightInTiles() {
		return WIDTH;
	}

	public int getWidthInTiles() {
		return HEIGHT;
	}
	
	public boolean visited(int x, int y) {
		return visited[x][y];
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean blocked(Mover mover, int x, int y) {
		
		return false;
	}

	@Override
	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static Plane getXyplane() {
		return xyPlane;
	}
	
}
