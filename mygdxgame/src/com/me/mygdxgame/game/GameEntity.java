package com.me.mygdxgame.game;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.me.mygdxgame.utils.DecalMgr;
import com.me.mygdxgame.utils.Point2i;

public class GameEntity{
	
	protected Point2i tilePosition;
	private int textureId;
	private Decal decal;
	
	public GameEntity(int textureId){
		this(textureId, new Point2i(0, 0));
	}
	
	public GameEntity(int textureId, Point2i tilePosition){
		this.tilePosition = tilePosition;
		this.textureId = textureId;
		this.setDecal(DecalMgr.build((byte)textureId));
	}
	
	public Point2i getTilePosition(){
		return this.tilePosition;
	}
	
	public void setPosition(int x, int y){
		this.tilePosition.x = x;
		this.tilePosition.y = y;
	}
	
	public void setPosition(Point2i tilePosition){
		this.tilePosition = tilePosition;
	}
	
	public int getTextureId() {return this.textureId;}

	public Decal getDecal() {
		return decal;
	}

	public void setDecal(Decal decal) {
		this.decal = decal;
	}
	
}
