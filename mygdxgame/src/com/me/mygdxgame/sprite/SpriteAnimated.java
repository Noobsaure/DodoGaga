package com.me.mygdxgame.sprite;

import com.badlogic.gdx.graphics.Color;
import com.me.mygdxgame.game.GameEntity;
import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.utils.Cst;

public class SpriteAnimated extends SpriteStatic{
	
	public SpriteAnimated(String textureName){
		super(textureName);
		//setOrigin(getWidth()/2, getHeight());
	}
	
	public SpriteAnimated(String textureName, int elevation){
		super(textureName, elevation);
	}
	
	public SpriteAnimated(String textureName, int elevation, Color c){
		super(textureName, elevation, c);
	}
	
	public SpriteAnimated(String textureName, int elevation, int r, int g, int b, int a){
		super(textureName, elevation, r, g, b, a);
	}
	
	public void update(GameMover entity){
		setPosition(entity.getRealPosition().x, entity.getRealPosition().y-getHeight()-getElevation());
	}
}
