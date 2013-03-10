package com.me.mygdxgame.utils;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.me.mygdxgame.data.Data;

public class DecalMgr {
	
	public static void init() {

	}
	
	public static Decal build(byte id) {
		return Decal.newDecal(Data.textures.get(id).getTextureRegion(), true);
	}
}
