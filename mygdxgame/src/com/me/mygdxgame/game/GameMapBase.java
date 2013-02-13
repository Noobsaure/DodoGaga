package com.me.mygdxgame.game;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataMap;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2i;

public abstract class GameMapBase {

	int mapId;
	public DataMap mapData;
	private Map<String, List<GameEvent>> tileEvents;
	private List<GameEvent> events;
	private static Pixmap mouseMap = new Pixmap(Gdx.files.internal("mouseMapIso.png"));
	public static GameMovable movableBattlerTest = new GameBattler(2);
	
	public void setup(int mapId) {
		this.mapId = mapId;
		this.mapData = Data.maps.get(mapId);
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
			tilePosition = new Point2i(2, 4);
			//tilePosition = new Point2i(rand.nextInt(getMapSize().x),rand.nextInt(getMapSize().y));
			innerTilePosition = new Point2i(rand.nextInt(Cst.NB_X_CELL),rand.nextInt(Cst.NB_Y_CELL));
			event = new GameEvent(2+rand.nextInt(2),tilePosition,innerTilePosition);
			//refreshEventPosition(tilePosition.x, tilePosition.y, event);
			addEventToTile(tilePosition,event);
		}
	}
	
	public static Point2i isoToTile(float x, float y) {
		int innerX = (int) (x % Cst.TILE_W);
		int innerY = (int) (y % Cst.TILE_H);
		
		Point2i res = new Point2i((int) x / Cst.TILE_W ,  (2 * ((int)y / Cst.TILE_H)));
		//System.out.println(res.x+" ; "+res.y);
		switch(mouseMap.getPixel(innerX, innerY)) {
		case -16776961://haut gauche
			res.y = res.y - 1;
			res.x = res.x - 1;
			break;
		case -65281://haut droite
			res.y = res.y - 1;
			break;
		case 16711935://bas gauche
			res.x = res.x - 1;
			res.y = res.y + 1;
			break;
		case 65535://bas droite
			res.y = res.y + 1;
			break;
		case -1://milieu
			break;
			default:
				res.x = -1;
				res.y = -1;
		}
		return res;
	}
	/*
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
	}*/
	
	public void removeEventFromTile(Point2i tile, GameEvent ev) {
		List<GameEvent> evs = eventsAt(tile);
		if(evs != null){
			evs.remove(ev);
			if(evs.isEmpty()) {
				tileEvents.remove(tile.getHashCode());
			}
		}
	}
	
	public void addEventToTile(Point2i tile, GameEvent ev) {
		List<GameEvent> evs = eventsAt(tile);
		if(evs == null) {
			evs = new ArrayList<GameEvent>();
			tileEvents.put(tile.getHashCode(), evs);
		}
		evs.add(ev);		
	}
	
	public List<GameEvent> eventsAt(int tileX, int tileY){
		Point2i tilePosition = new Point2i(tileX, tileY);
		return eventsAt(tilePosition);
	}
	
	public List<GameEvent> eventsAt(Point2i tilePosition){
		return tileEvents.get(tilePosition.getHashCode());
	}
	
	public void update(){
		updateEvents();
		//System.out.println(tileEvents.size());
		movableBattlerTest.update();
	}
	
	public void updateEvents(){
		for(GameEvent event : events){
			event.update();
		}
	}
	
	public Point2i getMapSize() {
		return mapData.tileSize;
	}

}
