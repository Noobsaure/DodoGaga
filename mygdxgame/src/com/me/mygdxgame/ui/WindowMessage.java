package com.me.mygdxgame.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.mygdxgame.mgr.WindowMgr;
import com.me.mygdxgame.utils.interval.interfaces.Interval;
import com.me.mygdxgame.utils.interval.special.WaitInterval;

public class WindowMessage extends WindowBase{

	int messageSpeed;
	Interval waitInterval;
	int index;
	String text;
	Label textLabel;
	
	long time;
	
	public WindowMessage(String title, Skin skin) {
		super(title, skin);
		waitInterval = new WaitInterval(0.01f);
		text = "Ceci est un test d'un\nmessage type RPG fait\nen 2 minutes !";
		index = 0;
		textLabel = new Label("", WindowMgr.skin);
		
		setPosition(0, 0);
		setWidth(200);
		row().fill().expand(true, true);
		textLabel.setAlignment(Align.left | Align.top);
		add(textLabel);
		row();
		
		setTitleAlignment(Align.left);
	}
	
	public String currentText(){
		return text.substring(0, index);
	}
	
	public char currentCharacter(){
		return text.charAt(index);
	}
	
	public boolean textFinish(){
		return index >= text.length();
	}
	
	public void update(){
		if(textFinish()){
			return;
		}
		
		if(currentCharacter() == ' '){
			index += 1;
		}
		
		if(waitInterval.isFinishedAndStart()){
			index += 1;
			textLabel.setText(currentText());
		}

	}

}
