package com.me.mygdxgame.mgr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.ui.Table2;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.IntervalTransformValue;
import com.me.mygdxgame.utils.interval.interfaces.IntervalPlayable;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;
import com.me.mygdxgame.utils.interval.transform.PosInterval;
import com.me.mygdxgame.utils.interval.transform.ScaleInterval;



public class WindowMgr {


	
	private static Stage stage;
	private static Skin skin;
	private static Label fpsLabel;
	public static Label spriteNumberLabel;
	
	public static void init(){
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		//stage.setCamera(Game.camera);
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("Graphics/Window/uiskin.json"));
		
		

		
		//table.setFillParent(true);
		
		//table.setPosition(-Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/2-10);
		//stage.addActor(table);
		

		//table.setOrigin(50, 50);
		
		fpsLabel = new Label("", skin);
		fpsLabel.setFillParent(true);
		stage.addActor(fpsLabel);
		fpsLabel.setAlignment(Align.left | Align.top);
		//fpsLabel.setPosition(0, Gdx.graphics.getHeight()/2);
		//table.add(fpsLabel);
		spriteNumberLabel = new Label("", skin);
		stage.addActor(spriteNumberLabel);
		spriteNumberLabel.setFillParent(true);
		spriteNumberLabel.setAlignment(Align.left | Align.top);
		spriteNumberLabel.setPosition(0, -20);
		spriteNumberLabel.setScaleY(3);
		
		//table.add(spriteNumberLabel);
		
		//System.out.println(Gdx.graphics.getHeight());
		//table.setPosition(0, 100);
		Label labelTest = new Label("Test interval", skin);
		Table2 table = new Table2();
		table.setFillParent(true);
		table.add(labelTest);
		stage.addActor(table);
		
		IntervalPlayable interval;
		interval = new PosInterval(table, 5, null, new Point2f(500,-300), "linear", "linear");
		interval.start();
		//interval = new ScaleInterval(table, 5, null, new Point2f(2, 2), "linear", "linear");
		//interval.start();
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
