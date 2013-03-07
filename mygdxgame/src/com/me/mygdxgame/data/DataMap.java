package com.me.mygdxgame.data;

import java.util.ArrayDeque;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.Vector3;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Debug;
import com.me.mygdxgame.utils.DecalMgr;
import com.me.mygdxgame.utils.Point2i;

public class DataMap extends DataBase{

	private Point2i sizeInTiles;
	private int maximumHeight;
	private byte[][] tilemap;
	private byte[][] heightmap;
	private ArrayDeque<Decal> decals;

	public DataMap(int id, String name, Point2i sizeInTiles) {
		super(id, name);
		this.sizeInTiles = new Point2i(sizeInTiles.x, sizeInTiles.y);
		tilemap = new byte[sizeInTiles.x][sizeInTiles.y];
		heightmap = new byte[sizeInTiles.x][sizeInTiles.y];
		maximumHeight = 0;
		decals = new ArrayDeque<Decal>();
	}

	public void loadMap() {
		
		//TODO
		Decal oneDecal;
		for(int j=0; j < sizeInTiles.y; j++) {
			for(int i=0; i < sizeInTiles.x; i++) {
				oneDecal = DecalMgr.build(tilemap[i][j]);
				oneDecal.setDimensions(2f,1f);
				oneDecal.setPosition((i-j), -(i+j)*0.5f + heightmap[i][j] * 0.25f, heightmap[i][j] - maximumHeight);
				if(heightmap[i][j] > 0)
					oneDecal.setColor(0.5f, 0.5f, 0.5f, 1f);
				decals.add(oneDecal);
			}
		}
	}

	public boolean isWall(int x, int y) {return getDataTile(x,y).isWall;}
	public DataTile getDataTile(int x, int y) {return Data.tiles.get(tilemap[x][y]);}
	public int getHeight(int x, int y) {return heightmap[x][y];}
	public int getMaximumHeight() {return maximumHeight;}
	public void setMaximumHeight(int maximumHeight) {this.maximumHeight = maximumHeight;}
	public byte getTile(int x, int y) {return tilemap[x][y];}
	public void setTile(int i, int j, byte value) {tilemap[i][j] = value;}
	public byte[][] getHeightmap() {return heightmap;}
	public void setHeight(int i, int j, byte value) {heightmap[i][j] = value;}
	public Point2i getSizeInTiles() {return sizeInTiles;}
	public ArrayDeque<Decal> getDecals() {return decals;}	


}
