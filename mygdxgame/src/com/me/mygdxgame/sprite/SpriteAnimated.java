package com.me.mygdxgame.sprite;

import com.badlogic.gdx.graphics.Color;
import com.me.mygdxgame.game.GameEntity;
import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.utils.Cst;

public class SpriteAnimated extends SpriteStatic{
	
	public SpriteAnimated(String textureFilename){
		super(textureFilename);
		//setOrigin(getWidth()/2, getHeight());
	}
	
	public SpriteAnimated(String textureFilename, int elevation){
		super(textureFilename, elevation);
	}
	
	public SpriteAnimated(String textureFilename, int elevation, Color color){
		super(textureFilename, elevation, color);
	}
	
	
	public void update(GameMover entity){
		setPosition(entity.getRealPosition().x, entity.getRealPosition().y-getHeight()-getElevation());
	}
  	
}
