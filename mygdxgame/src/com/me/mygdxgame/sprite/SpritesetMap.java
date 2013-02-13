package com.me.mygdxgame.sprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameEvent;
import com.me.mygdxgame.game.GameMovable;
import com.me.mygdxgame.mgr.SpriteMgr;
import com.me.mygdxgame.mgr.WindowMgr;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2i;

public class SpritesetMap {

	private SpriteBatch batch;
	private Point2i highlightedTile = new Point2i(-1,-1);

	//private byte[][] tilemap;

	public SpritesetMap(){
		createSpriteBatches();
		//createTilemap();
	}

	private void createSpriteBatches(){
		batch = new SpriteBatch();
	}

	/*
	private void createTilemap(){
		tilemap = new byte[Game.map.getMapSize().x][Game.map.getMapSize().y];
		for(int i=0; i < Game.map.getMapSize().x; i++){
			for(int j=0; j < Game.map.getMapSize().y; j++){
				tilemap
			}
		}
	}*/

	public void highlightTile(Point2i p) {
		highlightedTile = p;
	}


	public void update(){
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(Game.cam.combined);
		batch.begin();

		Vector3 inter = new Vector3();

		Ray pickRay = Game.cam.getPickRay(0, 0);
		Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, inter);

		float sx = inter.x;
		float sy = inter.y;

		pickRay = Game.cam.getPickRay(Gdx.graphics.getWidth(), 0);
		Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, inter);
		float sw = inter.x;

		pickRay = Game.cam.getPickRay(0, Gdx.graphics.getHeight());
		Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, inter);
		float sh = inter.y;

		Point2i pos = new Point2i(0, 0);
		SpriteTile spriteTile;
		SpriteTile highlightedSpriteTile;
		SpriteStatic sprite;
		List<GameEvent> events;
		List<SpriteBase> list = new ArrayList<SpriteBase>();
		
		int iStart = (int) (sx/Cst.TILE_W) - 1;
		int jStart = (int) (sy/Cst.TILE_HH) - 1;
		int iEnd = (int) (sw/Cst.TILE_W) + 1;
		int jEnd = (int) (sh/Cst.TILE_HH) + 1;
		
		iStart = Math.max(iStart, 0);
		jStart = Math.max(jStart, 0);
		iEnd = Math.min(iEnd, Game.map.getMapSize().x);
		jEnd = Math.min(jEnd, Game.map.getMapSize().y);
		
		int nbrendered = 0;
		
		for(int j=jStart; j < jEnd; j++) {
			for(int i=iStart; i < iEnd; i++) {
			
				pos.y = j*Cst.TILE_HH; //+cam y
				if (j % 2 == 0) {
					pos.x = i*Cst.TILE_W; //+cam x
				} else {
					pos.x = i*Cst.TILE_W + Cst.TILE_HW; //+cam x
				}
				
				spriteTile = SpriteMgr.getTile(Game.map.mapData.tilemap[i][j]);
				
				if(i == highlightedTile.x && j == highlightedTile.y) {
					highlightedSpriteTile = SpriteTile.getHighlightedTile(spriteTile);
					highlightedSpriteTile.setPosition(pos.x, pos.y - spriteTile.getElevation());
					highlightedSpriteTile.draw(batch);
				} else {
					spriteTile.setPosition(pos.x, pos.y - spriteTile.getElevation());
					spriteTile.draw(batch);
				}
				
				nbrendered++;
				
				events = Game.map.eventsAt(i,j);
				if(events != null){
					for(GameEvent event : events){
						if(event instanceof GameMovable){
							SpriteAnimated spriteAnim = SpriteMgr.getAnimated(event.getSpriteId());
							//System.out.println(event.getClass());
							//System.out.println(((GameMovable) event).getRealPosition());
							//sprite.setElevation(spriteTile.getElevation());
							spriteAnim.update((GameMovable) event);
							list.add(spriteAnim);
						}
						else if(event instanceof GameEvent){
							sprite = SpriteMgr.getStatic(event.getSpriteId());
							sprite.setElevation(spriteTile.getElevation());
							sprite.update(event);
							list.add(sprite);
						}
					}
				}
				
				
				Collections.sort(list, new Comparator<Sprite>() {
					@Override public int compare(Sprite s1, Sprite s2) {
						return (int) (s1.getY() - s2.getY());
					}
				});

				for(Sprite spr : list){
					spr.draw(batch);
					nbrendered++;
				}
				
				list.clear();
				
			}
		}
		
		WindowMgr.spriteNumberLabel.setText("Draw number: " + nbrendered);
		
		batch.end();

	}

}
