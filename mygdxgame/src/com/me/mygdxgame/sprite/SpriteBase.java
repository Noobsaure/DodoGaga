package com.me.mygdxgame.sprite;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;
import com.me.mygdxgame.game.GameEvent;
import com.me.mygdxgame.mgr.TextureMgr;

public class SpriteBase extends Sprite implements Cloneable{

	protected String textureFilename;
	
	public SpriteBase(String textureFilename){
		this(textureFilename, new Color(1,1,1,1));
	}
	
	public SpriteBase(String textureFilename, Color color){
		this.textureFilename = textureFilename;
		this.setTexture(this.textureFilename);
		this.setColor(color);
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
	
	public void setTexture(String textureFilename){
		Texture texture = TextureMgr.get(textureFilename);
		super.setTexture(texture);
		setRegion(0, 0, texture.getWidth(), texture.getHeight());
		setColor(1,1,1,1);
		setSize(Math.abs(texture.getWidth()), Math.abs(texture.getHeight()));
		flip(false, true);
	}
	
	public String getTextureFilename() {
		return this.textureFilename;
	}
	
  	public SpriteBase clone() {
  		try { return (SpriteBase) super.clone(); } catch(CloneNotSupportedException cnse) { return null; }
  	}
	
}
