package com.me.mygdxgame.game;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.Vector3;
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
	public GameBattler testBattler;


	public void setup(int mapId) {
		this.mapId = mapId;
		this.mapData = Data.maps.get(mapId);
		setupEvents();
	}

	public void setupEvents(){
		tileEvents = new Hashtable<String, List<GameEvent>>();
		events = new ArrayList<GameEvent>();
		/*for(int i=2;i<8;i++)
			for(int j=2;j<8;j++)
				gameBattlers.add(new GameBattler(3, new Point2i(i,j)));*/
		gameBattlers.add(new GameBattler(2, new Point2i(2,2)));
	}

	public static Point2f tileToIsof(int i, int j) {
		float x = i * Cst.TILE_HW - j * Cst.TILE_HW;
		float y = i * Cst.TILE_HH + j * Cst.TILE_HH;
		return new Point2f(x,y);
	}

	public static Point2i tileToIsoi(int i, int j) {
		int x = i * Cst.TILE_HW - (j - 1) * Cst.TILE_HW;
		int y = (i + 1) * Cst.TILE_HH + j * Cst.TILE_HH;
		return new Point2i(x,y);
	}

	public Point2f heightTileToIsof(int i, int j) {
		float x = i * Cst.TILE_HW - j * Cst.TILE_HW;
		float y = -(i * Cst.TILE_HH + j * Cst.TILE_HH - mapData.getHeight(i,j) * Cst.TILE_WALL_H);
		return new Point2f(x,y);
	}

	public Point2i heightTileToIsoi(int i, int j) {
		int x = i * Cst.TILE_HW - j * Cst.TILE_HW;
		int y = -(i * Cst.TILE_HH + j * Cst.TILE_HH - mapData.getHeight(i,j) * Cst.TILE_WALL_H);
		return new Point2i(x,y);
	}

	public static Point2i isoToTile(float x, float y) {
		int i = (int)(y/Cst.TILE_H + (x - Cst.TILE_HW)/Cst.TILE_W);
		int j = (int)(y/Cst.TILE_H - (x - Cst.TILE_HW)/Cst.TILE_W);
		return new Point2i(i,j);
	}

	public static Point2i isoToTile(Point2f p) {
		return isoToTile(p.x,p.y);
	}

	public Point2i heightIsoToTile(Point2f p) {
		return heightIsoToTile(p, true);
	}

	public Point2i heightIsoToTile(Point2f p, boolean ignoreCliff) {
		Point2i res = new Point2i(-1,-1);
		Point2i tmp;
		int mapHeight;
		int tmpHeight = mapData.getMaximumHeight();
		while(tmpHeight >= 0) {
			tmp = isoToTile(p.x + Cst.TILE_HW,-p.y + Cst.TILE_HH + tmpHeight * Cst.TILE_WALL_H);
			if(tmp.x < mapData.getSizeInTiles().x && tmp.y < mapData.getSizeInTiles().y && tmp.x >= 0 && tmp.y >= 0) {
				mapHeight = mapData.getHeight(tmp.x,tmp.y);
				if(mapHeight == tmpHeight) {
					res = tmp;
					break;
				} else if(mapHeight > tmpHeight) {
					if(!ignoreCliff)
						res = tmp;
					break;
				}
			}
			tmpHeight--;
		}
		return res;
	}

	public Point2i heightIsoToTile(float x, float y) {
		return heightIsoToTile(new Point2f(x,y));
	}

	public Point2i heightIsoToTile(float x, float y, boolean ignoreCliff) {
		return heightIsoToTile(new Point2f(x,y), ignoreCliff);
	}

	public Vector3 getOffsets(int x, int y, int tx, int ty, float zOffset) {
		Vector3 res = new Vector3(0,0,0);
		/*if(mapData.getHeight(x,y) > mapData.getHeight(tx, ty))
			res.z = 1;
		else
			res.z = mapData.getHeight(tx, ty) - mapData.getHeight(x, y);*/
		switch(x-tx) {
		case -1:
			res.x = Cst.TILE_HW;
			res.y = Cst.TILE_HH;
			break;
		case 1:
			res.x = -Cst.TILE_HW;
			res.y = -Cst.TILE_HH;
			break;
		case 0:
			switch(y-ty) {
			case -1:
				res.x = -Cst.TILE_HW;
				res.y = Cst.TILE_HH;
				break;
			case 1:
				res.x = +Cst.TILE_HW;
				res.y = -Cst.TILE_HH;				
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}


		return res;
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
		//System.out.println(tileEvents.size());
		for(GameBattler battler : gameBattlers){
			battler.update();
		}
	}

	public void updateEvents(){
		if(events != null)
			for(GameEvent event : events){
				event.update();
			}
	}

	public Point2i getMapSize() {return mapData.getSizeInTiles();}
	public int getHeight(Point2i p) {return mapData.getHeight(p.x,p.y);}
	public int getHeight(int i, int j) {return getHeight(new Point2i(i,j));}
	public List<GameBattler> getGameBattlers() {return gameBattlers;}
	public GameBattler getGameBattler(int id) {
		if(id < gameBattlers.size())
			return gameBattlers.get(id);
		else
			return null;
	}
	public int relativeTilePosition(Point2i from, Point2i to) {
		int res = 0;
		if(from.x > to.x && from.y == to.y)
			res = 1;
		else if(from.x < to.x && from.y == to.y)
			res = 2;
		else if(from.x == to.x && from.y > to.y)
			res = 3;
		else if(from.x == to.x && from.y < to.y)
			res = 4;
		return res;
	}

}
