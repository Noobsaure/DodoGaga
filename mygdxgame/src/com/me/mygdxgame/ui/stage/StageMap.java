package com.me.mygdxgame.ui.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.me.mygdxgame.mgr.StageMgr;

public class StageMap extends Stage {

	private Skin skin;
	
	public StageMap() {
		super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		Gdx.input.setInputProcessor(this);
		
		Texture uiBackground = new Texture(Gdx.files.internal("uiBase.png"));
		skin = StageMgr.skin;

		Table table = new Table();
		table.setBackground(new NinePatchDrawable(new NinePatch(uiBackground, 30, 30, 30, 30)));
		table.setFillParent(false);
		table.setWidth(getWidth());
		table.setHeight(100);
		table.bottom();
		
		addActor(table);
	}
}
