package com.me.mygdxgame.game;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataMap;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;

public abstract class GameMapBase {

	int mapId;
	public DataMap mapData;
	private Map<String, List<GameEvent>> tileEvents;
	private List<GameEvent> events;
	private List<GameBattler> gameBattlers = new ArrayList<GameBattler>();

	public void setup(int mapId) {
		this.mapId = mapId;
		this.mapData = Data.maps.get(mapId);
		setupEvents();
	}
	
	public void setupEvents(){
		tileEvents = new Hashtable<String, List<GameEvent>>();
		events = new ArrayList<GameEvent>();
		gameBattlers.add(new GameBattler(3, new Point2i(1,1)));
	}
	
	public static Point2f tileToIsof(int i, int j) {
		float x = i * Cst.TILE_HW - j * Cst.TILE_HW;
		float y = i * Cst.TILE_HH + j * Cst.TILE_HH;
		return new Point2f(x,y);
	}
	
	public static Point2i tileToIsoi(int i, int j) {
		int x = i * Cst.TILE_HW - j * Cst.TILE_HW;
		int y = i * Cst.TILE_HH + j * Cst.TILE_HH;
		return new Point2i(x,y);
	}
	
	public static Point2i isoToTile(float x, float y) {
		int i = (int)(0.5 * (y/Cst.TILE_HH + x/Cst.TILE_HW));
		int j = (int)(0.5 * (y/Cst.TILE_HH - x/Cst.TILE_HW));
		return new Point2i(i,j);
	}
	
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
		for(GameBattler battler : gameBattlers){
			battler.update();
		}
	}
	
	public void updateEvents(){
		for(GameEvent event : events){
			event.update();
		}
	}
	
	public Point2i getMapSize() {return mapData.tileSize;}	
	public List<GameBattler> getGameBattlers() {return gameBattlers;}

}
