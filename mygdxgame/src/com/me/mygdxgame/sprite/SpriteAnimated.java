package com.me.mygdxgame.sprite;

import com.me.mygdxgame.game.GameEntity;
import com.me.mygdxgame.game.GameMovable;
import com.me.mygdxgame.utils.Cst;

public class SpriteAnimated extends SpriteStatic{
	
	public SpriteAnimated(String textureName){
		super(textureName);
		elevation = 0;
	}
	
	public void update(GameMovable entity){
		setPosition(entity.getRealPosition().x, entity.getRealPosition().y-getHeight()-getElevation());
	}
}
