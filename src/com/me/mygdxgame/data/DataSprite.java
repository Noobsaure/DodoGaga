package com.me.mygdxgame.data;

public abstract class DataSprite extends DataBase{

	public float[] origin;
	
	public DataSprite(int id, String name){
		super(id, name);
		origin = new float[]{-1, -1};
	}
	
}
