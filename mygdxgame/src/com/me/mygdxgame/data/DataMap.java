package com.me.mygdxgame.data;

import java.util.ArrayDeque;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.DecalMgr;
import com.me.mygdxgame.utils.Point2i;

public class DataMap extends DataBase{

	private Point2i sizeInTiles;
	private int maximumHeight;
	private byte[][] tilemap;
	private byte[][] heightmap;
	private ArrayDeque<Decal> decals;
	private int maxZOrder;


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
				oneDecal.setPosition(
						(i-j) * Cst.TILE_HW,
						-((i+j) * Cst.TILE_HH - (heightmap[i][j] + 1) * Cst.TILE_WALL_H),
						getZOrder(i,j,heightmap[i][j]) * 0.000001f
						);
				decals.add(oneDecal);
				nbDecals++;

				int heightDiffx;
				int heightDiffy;
				int heightDiff;

				if(j < sizeInTiles.y - 1)
					heightDiffx = heightmap[i][j] - heightmap[i][j+1];
				else
					heightDiffx = heightmap[i][j];

				if(i < sizeInTiles.x - 1)
					heightDiffy = heightmap[i][j] - heightmap[i+1][j];
				else
					heightDiffy = heightmap[i][j];

				heightDiff = Math.max(heightDiffx, heightDiffy);

				if(heightDiff > 0) {
					for(int h=2;h<=heightDiff;h++) {
						oneDecal = DecalMgr.build((byte)3);
						oneDecal.setPosition(
								(i - j) * Cst.TILE_HW,
								-((i+j) * Cst.TILE_HH - (heightmap[i][j] - h) * Cst.TILE_WALL_H),
								getZOrder(i,j,heightmap[i][j] - h) * 0.000001f
								);
						decals.add(oneDecal);
						nbDecals++;
					}
				}
			}	
		}
		System.out.println(nbDecals);
	}

	public int getZOrder(int i, int j, int h) {
		return getZOrder(i, j, h, false);
	}

	public int getZOrder(int i, int j, int h, boolean dynamic) {
		int f1 = sizeInTiles.x*sizeInTiles.y;
		int m = Math.max(sizeInTiles.x,sizeInTiles.y);
		int f2 = (f1 + m) / 2;
		int res = (i+j) * f1
				+ h * f2
				+ i;
		if(dynamic)
			res += f1 + (f2 + m)/2;
		return res;	
	}
	
	public int getZOrder(int i, int j, float h, boolean dynamic) {
		int f1 = sizeInTiles.x*sizeInTiles.y;
		int m = Math.max(sizeInTiles.x,sizeInTiles.y);
		int f2 = (f1 + m) / 2;
		int res = (i+j) * f1
				+ (int)(h * f2)
				+ i;
		if(dynamic)
			res += f1 + (f2 + m)/2;
		return res;	
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
