package com.me.mygdxgame.sprite;

import com.badlogic.gdx.graphics.Color;
import com.me.mygdxgame.game.GameMover;

public class SpriteAnimated extends SpriteStatic{
	
	public SpriteAnimated(String textureFilename){
		super(textureFilename);
		setOrigin(getWidth()/2, getHeight());
	}
	
	public SpriteAnimated(String textureFilename, int elevation){
		super(textureFilename, elevation);
		setOrigin(getWidth()/2, getHeight());
	}
	
	public SpriteAnimated(String textureFilename, int elevation, Color color){
		super(textureFilename, elevation, color);
		setOrigin(getWidth()/2, getHeight());
	}
	
	public void update(GameMover entity){
		setPosition(entity.getPosition().x - getWidth()/2, entity.getPosition().y-getHeight()/2-getElevation());
	}
}
