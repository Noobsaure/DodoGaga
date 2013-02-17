package com.me.mygdxgame.mgr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.ui.Table2;
import com.me.mygdxgame.ui.WindowBase;
import com.me.mygdxgame.ui.WindowMessage;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.IntervalTransformValue;
import com.me.mygdxgame.utils.interval.interfaces.IntervalPlayable;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;
import com.me.mygdxgame.utils.interval.transform.PosInterval;
import com.me.mygdxgame.utils.interval.transform.ScaleInterval;

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
		
		Label labelTest = new Label("Test intervsdfsdfsdfs\ndfdsfsdfsdfal", skin);

		
		WindowMessage window = new WindowMessage("Gaga:", skin);
		//window.defaults().pad(20);
		// window.debug();
		/*
		window.setPosition(0, 0);
		window.row().fill().expand(true, true);
		labelTest.setAlignment(Align.left | Align.top);
		window.add(labelTest);
		window.row();*/
		/*
		window.add(buttonMulti);
		window.add(imgButton);
		window.add(imgToggleButton);
		window.row();*/
		/*
		window.add(checkBox);
		window.add(slider).minWidth(100).fillX().colspan(3);
		window.row();
		window.add(dropdown);
		window.add(textfield).minWidth(100).expandX().fillX().colspan(3);
		window.row();
		window.add(splitPane).fill().expand().colspan(4).maxHeight(200);
		window.row();
		window.add(passwordLabel).colspan(2);
		window.add(passwordTextField).minWidth(100).expandX().fillX().colspan(2);
		window.row();
		window.add(fpsLabel).colspan(4);
		*/
		//window.pack();
		
		stage.addActor(window);
		

/*
 * 		textfield.setTextFieldListener(new TextFieldListener() {
			public void keyTyped (TextField textField, char key) {
			if (key == '\n') textField.getOnscreenKeyboard().show(false);
			}
			});
			slider.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
			Gdx.app.log("UITest", "slider: " + slider.getValue());
			}
			});

			iconButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
			new Dialog("Some Dialog", skin, "dialog") {
			protected void result (Object object) {
			System.out.println("Chosen: " + object);
			}
			}.text("Are you enjoying this demo?").button("Yes", true).button("No", false).key(Keys.ENTER, true)
			.key(Keys.ESCAPE, false).show(stage);
			}
			});*/
			
		/*
		win.setWidth(300);
		win.setTitleAlignment(Align.left);
		stage.addActor(win);
		win.add(labelTest);
		labelTest.setAlignment(Align.left);*/
		//IntervalPlayable interval;
		//interval = new PosInterval(table, 5, null, new Point2f(500,-300), "linear", "linear");
		//interval.start();
		//interval = new ScaleInterval(table, 5, null, new Point2f(2, 2), "linear", "linear");
		//interval.start();
	}

	public void resize (int width, int height) {
		stage.setViewport(width, height, true);
	}

	public static void update(){
		//fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond() + "          ");
		for(WindowBase window : windows){
			window.update();
		}
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		Table.drawDebug(stage); // This is optional, but enables debug lines for tables.
	}
	
	public static void dispose() {
		stage.dispose();
	}
	
	public static void add(WindowBase window){
		windows.add(window);
	}
}
