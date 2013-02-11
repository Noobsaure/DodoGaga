package com.me.mygdxgame.sprite;

public class SpriteTile extends SpriteBase{
	
	
	
	public SpriteTile(String textureName){
		super(textureName);
	}
	
	public SpriteTile(String textureName, float r, float g, float b, float a){
		super(textureName,r,g,b,a);
	}
	
	public SpriteTile(String textureName, int elevation){
		super(textureName, elevation);
	}
	
	public SpriteTile(String textureName, int elevation, float r, float g, float b, float a){
		super(textureName,elevation,r,g,b,a);
	}
	
	public static SpriteTile getHighlightedTile(SpriteTile spr) {
		return new SpriteTile(spr.getTextureName(),spr.getElevation(), 0.75f, 0.75f, 0.75f, 1);
	}
}
