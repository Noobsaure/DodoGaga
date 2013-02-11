package com.me.mygdxgame.data;

public class DataTile extends DataBase{

	public boolean isWall;
	public DataSprite dataSprite;
	
	public DataTile(int id, String name, DataSprite dataSprite){
		super(id, name);
		this.dataSprite = dataSprite;
		this.isWall = false;
	}
	
	public DataTile(int id, String name, boolean isWall, DataSprite dataSprite){
		super(id, name);
		this.dataSprite = dataSprite;
		this.isWall = isWall;
	}
}
