package com.me.mygdxgame.mgr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class WindowMgr {

	private static Stage stage;
	private static Skin skin;
	
	public static void init(){
        stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
        Gdx.input.setInputProcessor(stage);

        //skin = new Skin(Gdx.files.internal("Graphics/Window/uiskin.json"));
    	//skin.getAtlas().getTextures().iterator().next().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        
    	//table.add(new Label("This is regular text.", skin));
    	
        
	}

	public void resize (int width, int height) {
		stage.setViewport(width, height, true);
	}

	public static void update(){
	        //Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		Table.drawDebug(stage); // This is optional, but enables debug lines for tables.
	}
	
	public static void dispose() {
		stage.dispose();
	}
	
}
