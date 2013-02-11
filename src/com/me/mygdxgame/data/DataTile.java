package com.me.mygdxgame.data;

public class DataTile extends DataSprite{

	public String textureName;
	public boolean isWall;
	
	public DataTile(int id, String name){
		super(id, name);
		textureName = "";
		isWall = false;
		//origin = new float[]{-1, -1};
	}
	
}
