package com.me.mygdxgame.sprite;

public class SpriteTile extends SpriteStatic{
	

	public SpriteTile(String textureName){
		this(textureName, 0);
	}
	
	public SpriteTile(String textureName, int elevation){
		this(textureName, elevation, 1, 1, 1, 1);
	}
	
	public SpriteTile(String textureName, int elevation, float r, float g, float b, float a){
		super(textureName, elevation, r, g, b, a);
	}
	
	
	public static SpriteTile getHighlightedTile(SpriteTile spr) {
		return new SpriteTile(spr.getTextureName(),spr.getElevation(), 0.75f, 0.75f, 0.75f, 1);
	}
}
