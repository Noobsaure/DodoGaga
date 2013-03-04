package com.me.mygdxgame.mgr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class WindowMgr {

	private static Stage stage;
	private static Skin skin;
	private static Label fpsLabel;
	public static Label spriteNumberLabel;
	
	public static void init(){
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("Graphics/Window/uiskin.json"));
		
		Table table = new Table();
		table.setFillParent(true);
		table.setPosition(-Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/2-10);
		stage.addActor(table);
		
		
		fpsLabel = new Label("", skin);
		table.add(fpsLabel);
		spriteNumberLabel = new Label("", skin);
		table.add(spriteNumberLabel);
	}

	public void resize (int width, int height) {
		stage.setViewport(width, height, true);
	}

	public static void update(){
		fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond() + "          ");
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		Table.drawDebug(stage); // This is optional, but enables debug lines for tables.
	}
	
	public static void dispose() {
		stage.dispose();
	}
	
}
