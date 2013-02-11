package com.me.mygdxgame.utils;

public class Cst {
	public static final int TILE_W = 256;
	public static final int TILE_H = 128;
	public static final int TILE_WALL_H = 64;
	
	public static final int MAP_WIDTH = 20;
	public static final int MAP_HEIGHT = 20;
	
	
	public static final int TILE_HW = TILE_W / 2;
	public static final int TILE_HH = TILE_H / 2;
	
	public static final int NB_X_CELL = 2;
	public static final int NB_Y_CELL = 2;
	
	public static final int CELL_W = TILE_W / NB_X_CELL;
	public static final int CELL_H = TILE_H / NB_Y_CELL;
	
	public static final int CELL_HW = CELL_W / 2;
	public static final int CELL_HH = CELL_H / 2;

	public static final byte FLOOR = 0;
	public static final byte WALL = 1;
	
}
