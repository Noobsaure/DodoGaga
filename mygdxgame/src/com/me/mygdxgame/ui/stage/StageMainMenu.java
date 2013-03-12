package com.me.mygdxgame.ui.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.me.mygdxgame.mgr.SceneMgr;
import com.me.mygdxgame.mgr.StageMgr;
import com.me.mygdxgame.scene.SceneMap;

public class StageMainMenu extends Stage {

	private Skin skin;

	public StageMainMenu() {
		super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		Gdx.input.setInputProcessor(this);

		skin = StageMgr.skin;



		Label newGameLabel = new Label("New Game", skin);
		Label exitLabel = new Label("Exit", skin);

		Button newGameButton = new Button(skin);
		newGameButton.setFillParent(false);
		newGameButton.add(newGameLabel);

		Button exitButton = new Button(skin);
		exitButton.setFillParent(false);
		exitButton.add(exitLabel);

		Table table = new Table();
		table.setFillParent(true);
		table.add(newGameButton).width(200).height(50);
		table.row();
		table.add(exitButton).width(200).height(50);

		newGameButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				SceneMgr.startSceneLater(new SceneMap());
			}
		});
		
		exitButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		
		
		addActor(table);
	}
}
