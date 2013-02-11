package com.me.mygdxgame.mgr;

import java.util.Hashtable;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureMgr {
	
	private static Map<String, Texture> cache = new Hashtable<String, Texture>();
	
	public static void add(String assetName){
		if(!cache.containsKey(assetName)){
			cache.put(assetName, new Texture(Gdx.files.internal(assetName)));
		}
	}
	
	public static Texture get(String assetName){
		add(assetName);
		return cache.get(assetName);
	}
	
}
