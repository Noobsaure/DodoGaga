package com.me.mygdxgame.sprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameEvent;
import com.me.mygdxgame.game.GameMap;
import com.me.mygdxgame.mgr.StaticMgr;
import com.me.mygdxgame.mgr.TileMgr;
import com.me.mygdxgame.utils.Cst;

public class SpritesetMap {

	private BitmapFont font;
	private SpriteBatch batch;
	private SpriteBatch batch2;
	private int highlightedI = -1;
	private int highlightedJ = -1;
	private SpriteTile highlightedTile;

	private byte[][] tilemap;

	public SpritesetMap(){
		font = new BitmapFont(Gdx.files.internal("arial-15.fnt"), false);

		createSpriteBatches();
		createTilemap();
	}

	private void createSpriteBatches(){
		batch = new SpriteBatch();
		batch2 = new SpriteBatch();
	}

	private void createTilemap(){
		tilemap = new byte[Game.map.getWidthInTiles()][Game.map.getHeightInTiles()];

		Random rand = new Random();

		for(int i=0; i<tilemap.length; i++){
			for(int j=0; j<tilemap[0].length; j++){
				int n = rand.nextInt(255);
				if(n < 200){
				tilemap[i][j] = Cst.FLOOR;
				} else{
					tilemap[i][j] = Cst.WALL;
				}
			}
		}
	}

	public void highlightTile(float x, float y) {
		highlightedI = isoToI(x,y);
		highlightedJ = isoToJ(x,y);
	}


	public void update(){
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(Game.cam.combined);
		batch.begin();

		Vector3 inter = new Vector3();

		Ray pickRay = Game.cam.getPickRay(0, 0);
		Intersector.intersectRayPlane(pickRay, GameMap.getXyplane(), inter);

		float sx = inter.x;
		float sy = inter.y;

		pickRay = Game.cam.getPickRay(Gdx.graphics.getWidth(), 0);
		Intersector.intersectRayPlane(pickRay, GameMap.getXyplane(), inter);
		float sw = inter.x - sx;

		pickRay = Game.cam.getPickRay(0, Gdx.graphics.getHeight());
		Intersector.intersectRayPlane(pickRay, GameMap.getXyplane(), inter);
		float sh = inter.y - sy;

		int iStart = isoToI(sx, sy) - 2;
		int jStart = isoToJ(sx + sw, sy) - 2;
		int iMax = isoToI(sx + sw, sy + sh) + 2;
		int jMax = isoToJ(sx, sy + sh) + 2;

		iStart = Math.max(iStart, 0);
		jStart = Math.max(jStart, 0);
		iMax = Math.min(iMax, Game.map.getWidthInTiles());
		jMax = Math.min(jMax, Game.map.getHeightInTiles());
		
		SpriteTile spriteTile;
		SpriteStatic spriteStatic;
		List<GameEvent> events;
		List<Sprite> list = new ArrayList<Sprite>();
		
		for (int i = iStart; i < iMax; i++) {
			for (int j = jStart; j < jMax; j++) {
				
				spriteTile = TileMgr.get(tilemap[i][j]);

				int x = i * Cst.TILE_HW - j * Cst.TILE_HW;
				int y = i * Cst.TILE_HH + j * Cst.TILE_HH - spriteTile.getElevation();
				
				if(i == highlightedI && j == highlightedJ) {
					highlightedTile = SpriteTile.getHighlightedTile(spriteTile);
					highlightedTile.setPosition(x, y);
					highlightedTile.draw(batch);
				} else {
					spriteTile.setPosition(x, y);
					spriteTile.draw(batch);
				}
				
				events = Game.map.eventsAt(i,j);
				if(events != null){
					for(GameEvent event : events){
						spriteStatic = StaticMgr.get(event.getId());
						spriteStatic.setElevation(spriteTile.getElevation());
						spriteStatic.update(event);
						list.add(spriteStatic);
					}
				}
				
				Collections.sort(list, new Comparator<Sprite>() {
					@Override public int compare(Sprite s1, Sprite s2) {
						return (int) (s1.getY() - s2.getY());
					}
				});

				for(Sprite spr : list){
					spr.draw(batch);
				}
			}
		}

		batch.end();

		batch2.begin();
		font.draw(batch2, String.valueOf(Gdx.graphics.getFramesPerSecond()), 20, 20);
		batch2.end();
	}

}
