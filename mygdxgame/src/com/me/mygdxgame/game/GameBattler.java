package com.me.mygdxgame.game;

import java.util.List;

import com.me.mygdxgame.utils.Point2i;

public class GameBattler extends GameEntity{
	
	private int dirX = 0;
	private int dirY = 0;
	private List<Point2i> currentPath;
	
	public GameBattler(int id) {
		super(id);
	}
	
	public GameBattler(int id, Point2i tilePosition, Point2i innerTilePosition) {
		super(id,tilePosition,innerTilePosition);
	}
	
	public void update() {
		//TODO check si les x prochaines cases du chemin sont libres. Retrouver un chemin via A* si c'est pas le cas.
	}
}
