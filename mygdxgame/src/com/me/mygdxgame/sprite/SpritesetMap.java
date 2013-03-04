package com.me.mygdxgame.sprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameEvent;
import com.me.mygdxgame.game.GameMap;
import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.ia.pathfinding.Path;
import com.me.mygdxgame.mgr.SpriteMgr;
import com.me.mygdxgame.mgr.WindowMgr;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2i;

public class SpritesetMap {

	private SpriteBatch batch;
	private Point2i highlightedTile = new Point2i(-1,-1);

	private Path path;

	public Point2i getHighlightedTile() {return highlightedTile;}
	public void setHighlightedTile(Point2i highlightedTile) {this.highlightedTile = highlightedTile;}
	public Path getPath() {return path;}
	public void setPath(Path path) {this.path = path;}

	public SpritesetMap(){createSpriteBatches();}

	private void createSpriteBatches(){batch = new SpriteBatch();}

	public void update(){
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(Game.camera.combined);
		batch.begin();

		Vector3 inter = new Vector3();

		Ray pickRay = Game.camera.getPickRay(0, 0);
		Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, inter);

		float sx = inter.x;
		float sy = inter.y;

		pickRay = Game.camera.getPickRay(Gdx.graphics.getWidth(), 0);
		Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, inter);
		float sw = inter.x;

		pickRay = Game.camera.getPickRay(0, Gdx.graphics.getHeight());
		Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, inter);
		float sh = inter.y;

		Point2i pos = new Point2i(0, 0);
		SpriteTile spriteTile;
		SpriteTile highlightedSpriteTile;
		SpriteStatic sprite;
		List<GameEvent> events;
		List<SpriteBase> list = new ArrayList<SpriteBase>();

		int iStart = (int) (sx/Cst.TILE_W);
		int jStart = (int) (sy/Cst.TILE_HH);
		int iEnd = (int) (sw/Cst.TILE_W);
		int jEnd = (int) (sh/Cst.TILE_HH);

		iStart = Math.max(iStart, 0);
		jStart = Math.max(jStart, 0);
		iEnd = Math.min(iEnd, Game.map.getMapSize().x);
		jEnd = Math.min(jEnd, Game.map.getMapSize().y);

		int nbrendered = 0;

		for(int j=0; j < Game.map.getMapSize().y; j++) {
			for(int i=0; i < Game.map.getMapSize().x; i++) {

				//pos = GameMap.tileToIsoi(i,j);
				pos = Game.map.heightTileToIsoi(i,j);
				//pos.y = pos.y - Cst.TILE_WALL_H * Game.map.mapData.heightmap[i][j];

				spriteTile = SpriteMgr.getTile(Game.map.mapData.tilemap[i][j], false);

				int heightDiff;

				if(j < Game.map.getMapSize().y - 1)
					heightDiff = Game.map.mapData.heightmap[i][j] - Game.map.mapData.heightmap[i][j+1];
				else
					heightDiff = Game.map.mapData.heightmap[i][j];

				if(heightDiff > 0) {
					for(int k=0;k<heightDiff;k++) {
						sprite = SpriteMgr.getStatic(4); //wallLeft
						sprite.setPosition(pos.x + Cst.TILE_WALL_HW, pos.y + sprite.getHeight() + Cst.TILE_WALL_H * (k+2));
						sprite.draw(batch);
						nbrendered++;
					}
				}

				if(i < Game.map.getMapSize().x - 1)
					heightDiff = Game.map.mapData.heightmap[i][j] - Game.map.mapData.heightmap[i+1][j];
				else
					heightDiff = Game.map.mapData.heightmap[i][j];

				if(heightDiff > 0) {
					for(int k=0;k<heightDiff;k++) {
						sprite = SpriteMgr.getStatic(6); //wallRight
						sprite.setPosition(pos.x + Cst.TILE_WALL_HW + Cst.TILE_HW, pos.y + sprite.getHeight() + Cst.TILE_WALL_H * (k+2));
						sprite.draw(batch);
						nbrendered++;
					}
				}
				
				if(i == highlightedTile.x && j == highlightedTile.y) {
					highlightedSpriteTile = SpriteTile.getHighlightedTile(spriteTile);
					highlightedSpriteTile.setPosition(pos.x, pos.y);
					highlightedSpriteTile.draw(batch);
				} else {
					spriteTile.setPosition(pos.x, pos.y);
					spriteTile.draw(batch);
				}

				nbrendered++;

				events = Game.map.eventsAt(i,j);
				if(events != null){
					for(GameEvent event : events){
						if(event instanceof GameMover){
							SpriteAnimated spriteAnim = SpriteMgr.getAnimated(event.getSpriteId());
							spriteAnim.update((GameMover) event);
							list.add(spriteAnim);
						}
						else if(event instanceof GameEvent){
							sprite = SpriteMgr.getStatic(event.getSpriteId());
							sprite.update(event);
							list.add(sprite);
						}
					}
				}

				/*if(path != null) {
					Step step;
					for(int a=0;a<path.getLength();a++) {
						step = path.getStep(a);
						SpriteStatic spr = SpriteMgr.getStatic(4);
						spr.setColor(1, 1, 1, 0.25f);
						pos = GameMap.tileToIsoi(step.getX(),step.getY());
						spr.setPosition(pos.x - Cst.TILE_HW, pos.y + Cst.TILE_H);
						list.add(spr);
					}
				}*/

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
