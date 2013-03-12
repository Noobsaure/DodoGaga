package com.me.mygdxgame.mgr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.mygdxgame.ui.WindowBase;
import com.me.mygdxgame.ui.WindowMessage;

import java.util.ArrayList;
import java.util.List;

public class WindowMgr {
	
	public static Stage stage;
	public static Skin skin;
	private static Label fpsLabel;
	public static Label spriteNumberLabel;
	
	private static List<WindowBase> windows = new ArrayList<WindowBase>();
	
	public static void init(){
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("Graphics/Window/uiskin.json"));
		fpsLabel = new Label("", skin);
		fpsLabel.setFillParent(true);
		stage.addActor(fpsLabel);
		fpsLabel.setAlignment(Align.left | Align.top);
		WindowBase window = new WindowMessage("Gaga:", skin);
		stage.addActor(window);
	}

	public static void resize (int width, int height) {
		stage.setViewport(width, height, true);
	}

	public static void update(){
		fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond() + "          ");
		/*for(WindowBase window : windows){
			//window.update();
		}
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();*/
		
		Table.drawDebug(stage); // This is optional, but enables debug lines for tables.
	}
	
	public static void dispose() {
		stage.dispose();
	}
	
	public static void add(WindowBase window){
		windows.add(window);
	}
}
