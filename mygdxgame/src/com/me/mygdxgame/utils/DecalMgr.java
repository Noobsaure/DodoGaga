package com.me.mygdxgame.utils;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.me.mygdxgame.data.Data;

public class DecalMgr {
	
	public static void init() {
		/*for(int i=0;i<textures.length;i++) {
			Texture image = new Texture(Gdx.files.internal(textures[i]));
			image.setFilter(Texture.TextureFilter.Linear,
					Texture.TextureFilter.Linear);
			image.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
			wi[i] = image.getWidth();
			he[i] = image.getHeight();
			textureRegion[i] = new TextureRegion(image);
		}*/
	}
	
	public static Decal build(byte id) {
		return Decal.newDecal(Data.textures.get(id).getTextureRegion(), true);
	}

	/*public void faceCamera(Camera oCam) {
		sprite.lookAt(oCam.position.cpy(), oCam.up.cpy().nor());
	}*/
}
