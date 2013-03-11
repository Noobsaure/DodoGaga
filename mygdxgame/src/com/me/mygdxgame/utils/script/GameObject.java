package com.me.mygdxgame.utils.script;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.me.mygdxgame.sprite.SpriteBase;
import com.me.mygdxgame.utils.DecalMgr;

public class GameObject {

	IGameObject object;
	public Decal sprite;
	
	public GameObject(){
		object = ScriptMgr.loadScript("CallJava.new");
		sprite = Decal.newDecal(new TextureRegion(new Texture(Gdx.files.internal("wall_iso2.png"))));
		object.setup(sprite);
	}
	
	public void update(){
		//sprite.setPosition(sprite.getX()+1, 0, 500);
		object.update();
	}
	
}
