package com.me.mygdxgame.sprite;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.decals.GroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.SimpleOrthoGroupStrategy;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameBattler;
import com.me.mygdxgame.ia.pathfinding.Path;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.DecalMgr;
import com.me.mygdxgame.utils.Point2i;
import com.me.mygdxgame.utils.script.GameObject;

public class MapRenderer {

	private Decal highlightedTile;
	private boolean highlight = false;

	private Path path;
	private DecalBatch decalBatch;

	GameObject objectRuby;
	
	public void setHighlightedTile(Point2i highlightedTile) {
		if(highlightedTile.x != -1) {
			highlight = true;
			int i = highlightedTile.x;
			int j = highlightedTile.y;
			this.highlightedTile.setPosition(
					(i-j) * Cst.TILE_HW,
					-((i+j) * Cst.TILE_HH - (Game.map.getHeight(i, j)) * Cst.TILE_WALL_H),
					Game.map.mapData.getZOrder(i,j,Game.map.getHeight(i, j)) * 0.000001f
					);
		} else
			highlight = false;
	}
	public Path getPath() {return path;}
	public void setPath(Path path) {this.path = path;}

	public MapRenderer(){
		Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
		Gdx.gl.glDepthFunc(GL10.GL_LESS);
		GroupStrategy strategy = new SimpleOrthoGroupStrategy();
		decalBatch = new DecalBatch(strategy);
		highlightedTile = DecalMgr.build((byte)5);
		highlightedTile.setDimensions(Cst.TILE_W,Cst.TILE_H);
		
		//objectRuby = new GameObject();
	}

	public void update(){
		GL10 gl = Gdx.gl10;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		Game.camera.update();
		Game.camera.apply(gl);

		Vector3 inter = new Vector3();

		Ray pickRay = Game.camera.getPickRay(0, 0);
		Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, inter);

		float sx = inter.x - Cst.TILE_W;
		float sy = inter.y + Cst.TILE_H;

		pickRay = Game.camera.getPickRay(Gdx.graphics.getWidth(), 0);
		Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, inter);
		float sw = inter.x + Cst.TILE_W;

		pickRay = Game.camera.getPickRay(0, Gdx.graphics.getHeight());
		Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, inter);
		float sh = inter.y - Cst.TILE_H;

		for(Decal oneDecal : Game.map.mapData.getDecals()) {
			if(oneDecal.getPosition().x >= sx && oneDecal.getPosition().y <= sy
					&& oneDecal.getPosition().x < sw && oneDecal.getPosition().y > sh) {
				decalBatch.add(oneDecal);
			}
		}

		if(highlight)
			decalBatch.add(highlightedTile);

		for(GameBattler oneBattler : Game.map.getGameBattlers()) {
			decalBatch.add(oneBattler.getDecal());
		}

		//objectRuby.update();
		//decalBatch.add(objectRuby.sprite);
		
		decalBatch.flush();

		//Game.map.mapData.mesh.render(GL10.GL_TRIANGLES, 0, 7);


		/*batch.setProjectionMatrix(Game.camera.combined);
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

				//spriteTile = SpriteMgr.getTile(Game.map.mapData.tilemap[i][j], false);
				decal = DecalMgr.build(Game.map.mapData.tilemap[i][j]);
				decal.setPosition(pos.x, , pos.z);
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

				if(path != null) {
					Step step;
					for(int a=0;a<path.getLength();a++) {
						step = path.getStep(a);
						SpriteStatic spr = SpriteMgr.getStatic(4);
						spr.setColor(1, 1, 1, 0.25f);
						pos = GameMap.tileToIsoi(step.getX(),step.getY());
						spr.setPosition(pos.x - Cst.TILE_HW, pos.y + Cst.TILE_H);
						list.add(spr);
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

		batch.end();*/
	}
}
