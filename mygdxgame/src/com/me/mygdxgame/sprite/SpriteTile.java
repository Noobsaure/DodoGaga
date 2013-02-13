package com.me.mygdxgame.sprite;

import com.badlogic.gdx.graphics.Color;

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
	
	public SpriteTile(String textureName, int elevation, Color c){
		super(textureName, elevation, c.r, c.g, c.b, c.a);
	}
	
	
	public static SpriteTile getHighlightedTile(SpriteTile spr) {
		return new SpriteTile(spr.getTextureName(),spr.getElevation(), 0.75f, 0.75f, 0.75f, 1);
	}
}
