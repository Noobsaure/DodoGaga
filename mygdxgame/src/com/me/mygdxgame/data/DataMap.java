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
		int nbDecals = 0;
		for(int j=0; j < sizeInTiles.y; j++) {
			for(int i=0; i < sizeInTiles.x; i++) {
				oneDecal = DecalMgr.build(tilemap[i][j]);
				oneDecal.setDimensions(Cst.TILE_W,Cst.TILE_H);
				oneDecal.setPosition((i-j) * Cst.TILE_HW,
						-(i+j) * Cst.TILE_HH + heightmap[i][j] * Cst.TILE_WALL_H,
						heightmap[i][j] - maximumHeight);
				decals.add(oneDecal);
				nbDecals++;		
		
				int heightDiff;

				if(j < sizeInTiles.y - 1)
					heightDiff = heightmap[i][j] - heightmap[i][j+1];
				else
					heightDiff = heightmap[i][j];

				if(heightDiff > 0) {
					for(int h=0;h<heightDiff;h++) {
						oneDecal = DecalMgr.build((byte)1);
						oneDecal.setDimensions(Cst.TILE_HW,Cst.TILE_H);
						oneDecal.setPosition((i - j) * Cst.TILE_HW - Cst.TILE_WALL_HW,
								-(i + j) * Cst.TILE_HH + (heightmap[i][j]-h-1) * Cst.TILE_WALL_H,
								(heightmap[i][j]-h) - maximumHeight);
						decals.add(oneDecal);
						nbDecals++;
					}
				}

				if(i < sizeInTiles.x - 1)
					heightDiff = heightmap[i][j] - heightmap[i+1][j];
				else
					heightDiff = heightmap[i][j];

				if(heightDiff > 0) {
					for(int h=0;h<heightDiff;h++) {
						oneDecal = DecalMgr.build((byte)2);
						oneDecal.setDimensions(Cst.TILE_HW,Cst.TILE_H);
						oneDecal.setPosition((i - j) * Cst.TILE_HW + Cst.TILE_WALL_HW,
								-(i + j) * Cst.TILE_HH + (heightmap[i][j]-h-1) * Cst.TILE_WALL_H,
								(heightmap[i][j]-h) - maximumHeight);
						decals.add(oneDecal);
						nbDecals++;
					}
				}
			}	
		}
		System.out.println(nbDecals);
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
