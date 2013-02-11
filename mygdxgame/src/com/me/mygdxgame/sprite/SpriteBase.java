package com.me.mygdxgame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;
import com.me.mygdxgame.game.GameEvent;
import com.me.mygdxgame.mgr.TextureMgr;

public class SpriteBase extends Sprite{

	protected String textureName;
	
	public SpriteBase(String textureName){
		this(textureName, 1, 1, 1, 1);
	}
	
	public SpriteBase(String textureName, float r, float g, float b, float a){
		this.textureName = textureName;
		this.setTexture(this.textureName, r, g, b, a);
	}
	
	@Override
	public float getX(){
		return super.getX() + getOriginX();
	}
	
	@Override
	public float getY(){
		return super.getY() + getOriginY();
	}
	
	@Override
	public void setX(float x){
		super.setX(x - getOriginX());
	}
	
	@Override
	public void setY(float y){
		super.setY(y - getOriginY());
	}
	
	@Override
	public void setPosition(float x, float y){
		super.setPosition(x - getOriginX(), y - getOriginY());
	}
	
	@Override
	public void setOrigin(float originX, float originY) {
		setPosition(getX() + getOriginX(), getY() + getOriginY());
		super.setOrigin(originX, originY);
		setPosition(getX() - getOriginX(), getY() - getOriginY());
	}

	public void setTexture(String textureName){
		setTexture(textureName, 1, 1, 1, 1);
	}
	
	public void setTexture(String textureName, float r, float g, float b, float a){
		Texture texture = TextureMgr.get(textureName);
		super.setTexture(texture);
		setRegion(0, 0, texture.getWidth(), texture.getHeight());
		setColor(r, g, b, a);
		setSize(Math.abs(texture.getWidth()), Math.abs(texture.getHeight()));
		flip(false, true);
	}
	
	public String getTextureName() {
		return this.textureName;
	}
	

}
