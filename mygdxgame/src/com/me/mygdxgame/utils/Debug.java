package com.me.mygdxgame.utils;

public class Debug {

	private static boolean debug = false;
	
	public static void debugMsg(String msg) {
		if(debug)
			System.out.println(msg);
	}
	
	public static void setDebugMode(boolean on) {
		debug = on;
	}
}
