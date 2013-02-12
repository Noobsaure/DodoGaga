package com.me.mygdxgame.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;

public abstract class GameMapBase {

	private Map<String, List<GameEvent>> tileEvents;
	private List<GameEvent> events;
	protected Point2i mapSize;
	private Pixmap mouseMap;
	
	public GameMapBase() {
		mapSize = new Point2i();
		mouseMap = new Pixmap(Gdx.files.internal("mouseMapIso.png"));
	}
	
	public void setup(Point2i mapSize) {
		this.mapSize.x = mapSize.x;
		this.mapSize.y = mapSize.y;
		setupEvents();
	}
	
	public void setupEvents(){
		tileEvents = new Hashtable<String, List<GameEvent>>();
		events = new ArrayList<GameEvent>();
		
		Random rand = new Random();
		GameEvent event;
		Point2i tilePosition;
		Point2i innerTilePosition;	
		
		for(int i=0; i<100; i++){
			tilePosition = new Point2i(rand.nextInt(mapSize.x),rand.nextInt(mapSize.y));
			innerTilePosition = new Point2i(rand.nextInt(Cst.NB_X_CELL),rand.nextInt(Cst.NB_Y_CELL));
			event = new GameEvent(rand.nextInt(2),tilePosition,innerTilePosition);
			refreshEventPosition(tilePosition.x, tilePosition.y, event);
		}
	}
	
	public Point2i isoToTile(float x, float y) {
		int innerX = (int) (x % Cst.TILE_W);
		int innerY = (int) (y % Cst.TILE_H);
		Color c = new Color(mouseMap.getPixel(innerX, innerY));
		System.out.println(c.getRed()+" "+c.getGreen()+" "+c.getBlue());
		System.out.println(mouseMap.getPixel(innerX, innerY));		
		return new Point2i(0,0);
	}
	/*
	public void convertToScreen(Vector2 point){
		float x = point.x / Cst.TILE_W;
		float y = point.y / Cst.TILE_H;
		point.x = - (y * Cst.TILE_HW) + (x * Cst.TILE_HW);
		point.y = (y * Cst.TILE_HH) + (x * Cst.TILE_HH);
	}
	
	public void convertToScreen(Point2f point){
		float x = point.x / Cst.TILE_W;
		float y = point.y / Cst.TILE_H;
		point.x = - (y * Cst.TILE_HW) + (x * Cst.TILE_HW);
		point.y = (y * Cst.TILE_HH) + (x * Cst.TILE_HH);
	}
	
	public void convertToScreen(Point2i point){
		int x = point.x / Cst.TILE_W;
		int y = point.y / Cst.TILE_H;
		point.x = - (y * Cst.TILE_HW) + (x * Cst.TILE_HW);
		point.y = (y * Cst.TILE_HH) + (x * Cst.TILE_HH);
	}
	*/
	public void refreshEventPosition(int tileX, int tileY, GameEvent ev){
		List<GameEvent> evs;
		
		evs = eventsAt(ev.getTilePosition());
		if(evs != null){
			//evs.remove(ev);
			if(evs.size() == 0){
				//tileEvents.remove((new Point2i(tileX, tileY)).getHashCode());
			}
		}
		
		evs = eventsAt(tileX, tileY);
		if(evs == null){
			evs = new ArrayList<GameEvent>();
			tileEvents.put(new Point2i(tileX, tileY).getHashCode(), evs);
		}
		evs.add(ev);
	
	}
	
	public List<GameEvent> eventsAt(int tileX, int tileY){
		Point2i tilePosition = new Point2i(tileX, tileY);
		return eventsAt(tilePosition);
	}
	
	public List<GameEvent> eventsAt(Point2i tilePosition){
		return tileEvents.get(tilePosition.getHashCode());
	}
	
	public void update(){
		updateEvents();
	}
	
	public void updateEvents(){
		for(GameEvent event : events){
			event.update();
		}
	}
	
	public Point2i getMapSize() {
		return mapSize;
	}

}
