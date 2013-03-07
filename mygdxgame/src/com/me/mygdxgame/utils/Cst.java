package com.me.mygdxgame.utils;

import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;

public class Cst {
	
	public static final Plane XY_PLANE = new Plane(new Vector3(0, 0, 1), 0);
	
	public static final int TILE_W = 256;
	public static final int TILE_H = 128;
	public static final int TILE_WALL_H = 32;
	public static final int TILE_WALL_W = 128;
	public static final int TILE_WALL_HW = 64;
	
	public static final Point2i MAP_SIZE = new Point2i(100, 100);
	
	public static final int TILE_HW = TILE_W / 2;
	public static final int TILE_HH = TILE_H / 2;

	public static final byte FLOOR = 0;
	public static final byte WALL = 1;
	
	public static final int NB_TEXTURES = 2;
	
}
