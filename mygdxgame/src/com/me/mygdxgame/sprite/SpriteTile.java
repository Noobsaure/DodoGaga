package com.me.mygdxgame.sprite;

import com.badlogic.gdx.graphics.Color;

public class SpriteTile extends SpriteStatic{
	

	public SpriteTile(String textureFilename){
		this(textureFilename, 0);
	}
	
	public SpriteTile(String textureFilename, int elevation){
		this(textureFilename, elevation, new Color(1,1,1,1));
	}
	
	public SpriteTile(String textureFilename, int elevation, Color color){
		super(textureFilename, elevation, color);
	}
	
	public static SpriteTile getHighlightedTile(SpriteTile spr) {
		return new SpriteTile(spr.getTextureFilename(),spr.getElevation(), new Color(0.75f, 0.75f, 0.75f, 1));
	}
	
}
