package com.me.mygdxgame.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DataTexture extends DataBase {
	
	private TextureRegion textureRegion;
	private float width;
	private float height;

	public DataTexture(int id, String name, String textureName) {
		super(id, name);
		Texture image = new Texture(Gdx.files.internal(textureName));
		image.setFilter(Texture.TextureFilter.Linear,
				Texture.TextureFilter.Linear);
		image.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		width = image.getWidth();
		height = image.getHeight();
		textureRegion = new TextureRegion(image);
	}

	public TextureRegion getTextureRegion() {return textureRegion;}
	public float getWidth() {return width;}
	public float getHeight() {return height;}
	
}
