package com.me.mygdxgame.utils.script;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.jruby.Ruby;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.runtime.builtin.IRubyObject;


public class ScriptMgr {

	public static final Ruby runtime = Ruby.newInstance();
	
	public static void init(){
    	try {
			runtime.loadFile("assets/Scripts/call_java.rb", new FileInputStream ("assets/Scripts/call_java.rb"), false);
			runtime.loadFile("assets/Scripts/call_java2.rb", new FileInputStream ("assets/Scripts/call_java2.rb"), false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static IGameObject loadScript(String filename){
		IRubyObject rawRuby = runtime.evalScriptlet(filename);
        return (IGameObject) JavaEmbedUtils.rubyToJava(runtime, rawRuby, IGameObject.class);
	}
	
}
