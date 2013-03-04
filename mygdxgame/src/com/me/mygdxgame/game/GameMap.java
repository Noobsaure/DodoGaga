package com.me.mygdxgame.game;

import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataMap;
import com.me.mygdxgame.ia.pathfinding.Mover;
import com.me.mygdxgame.ia.pathfinding.TileBasedMap;
import com.me.mygdxgame.utils.Point2i;

public class GameMap extends GameMapBase implements TileBasedMap {

	private boolean[][] visited;
	
	public GameMap(){
		super();
	}
	
	public void clearVisited(){
		for (int x=0; x < getMapSize().x; x++){
			for (int y=0; y < getMapSize().y; y++){
				visited[x][y] = false;
			}
		}
	}
	
	@Override
	public void update(){
		super.update();
	}
	
	public boolean visited(int x, int y) {
		return visited[x][y];
	}

	public void pathFinderVisited(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public boolean blocked(GameMover mover, int x, int y) {
		
		return getDataMap().isWall(x, y);
	}

	public float getCost(GameMover mover, int sx, int sy, int tx, int ty) {
		return 0;
	}
	
	public float getCost(GameMover mover, Point2i start, Point2i finish) {
		return getCost(mover,start.x,start.y,finish.x, finish.y);
	}
	
	public DataMap getDataMap() {
		return Data.maps.get(mapId);
	}
}
