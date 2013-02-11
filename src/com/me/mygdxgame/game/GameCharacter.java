package com.me.mygdxgame.game;

import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.sprite.SpriteCharacter;

public class GameCharacter{
	
	protected Vector2 tilePosition;
	protected Vector2 position;
	public String textureName;
	
	public Vector2 getTilePosition(){
		return tilePosition;
	}
	
	public GameCharacter(){
		tilePosition = new Vector2(0, 0);
		position = new Vector2(0, 0);
		textureName = "";
		SpriteCharacter sprite = new SpriteCharacter(this);
	}
	
}
