package com.me.mygdxgame.data;

public class DataSprite extends DataBase{

	public String textureFilename;
	
	public DataSprite(int id, String name, String textureName){
		super(id, name);
		this.textureFilename = textureName;
	}
	
}
