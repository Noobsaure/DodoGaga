package com.me.mygdxgame.ui.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.esotericsoftware.tablelayout.Cell;
import com.me.mygdxgame.mgr.SceneMgr;
import com.me.mygdxgame.mgr.StageMgr;
import com.me.mygdxgame.scene.SceneMap;

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
		table.pad(5);
		
		Label mainMenuLabel = new Label("Main Menu", skin);

		Button mainMenuButton = new Button(skin);
		mainMenuButton.setFillParent(false);
		mainMenuButton.add(mainMenuLabel).center();
		
		table.top().left();
		table.add(mainMenuButton).width(100).height(20);
		
		addActor(table);
		
		mainMenuButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				System.out.println("changed");
			}
		});
	}
}
