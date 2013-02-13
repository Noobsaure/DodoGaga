package com.me.mygdxgame.data;

import com.me.mygdxgame.utils.Point2i;

public class DataMap extends DataBase{

	public Point2i tileSize;
	public byte[][] tilemap;
	
	public DataMap(int id, String name, Point2i tileSize) {
		super(id, name);
		this.tileSize = new Point2i(tileSize.x, tileSize.y);
		tilemap = new byte[tileSize.x][tileSize.y];
	}

}
