package com.me.mygdxgame.game;

import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;

public class GameBattler extends GameMovable{
	
	public GameBattler(int id, Point2f realPosition) {
		super(id, realPosition);
	}
	
	//public GameBattler(int id, Point2i tilePosition, Point2i innerTilePosition) {
	//	super(id,tilePosition,innerTilePosition);
	//}
	
	public void update() {
		super.update();
		//TODO check si les x prochaines cases du chemin sont libres. Retrouver un chemin via A* si c'est pas le cas.
	}
}
