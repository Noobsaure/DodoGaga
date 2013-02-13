package com.me.mygdxgame.game;

import com.me.mygdxgame.ia.pathfinding.Mover;
import com.me.mygdxgame.ia.pathfinding.TileBasedMap;

public class GameMap extends GameMapBase implements TileBasedMap {

	private boolean[][] visited;
	//private final BufferedImage mouseMap = new Bu;
	
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

	public boolean blocked(Mover mover, int x, int y) {
		
		return false;
	}

	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidthInTiles() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeightInTiles() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
