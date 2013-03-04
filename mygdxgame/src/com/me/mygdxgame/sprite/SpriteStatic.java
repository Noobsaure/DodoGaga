package com.me.mygdxgame.sprite;

import com.badlogic.gdx.graphics.Color;
import com.me.mygdxgame.game.GameEntity;
import com.me.mygdxgame.game.GameMap;

public class SpriteStatic extends SpriteBase{
  	
	protected int elevation;
	
	public SpriteStatic(String textureName){
		this(textureName, 0);
		super.setOrigin(getWidth()/2, getHeight());
	}
	
	public SpriteStatic(String textureName, int elevation){
		this(textureName, elevation, new Color(1,1,1,1));
	}
	
	public SpriteStatic(String textureName, int elevation, Color color){
		super(textureName, color);
		this.elevation = elevation;
	}
	
	public void update(GameEntity entity){
		int i = entity.getTilePosition().x;
		int j = entity.getTilePosition().y;
		setPosition(GameMap.tileToIsof(i,j));
	}
	
	public int getElevation() {
		return elevation;
	}
	
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
  	
}
