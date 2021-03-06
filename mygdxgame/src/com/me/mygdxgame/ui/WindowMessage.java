package com.me.mygdxgame.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.mygdxgame.mgr.WindowMgr;

public class WindowMessage extends WindowBase{

	int messageSpeed;
	int index;
	String text;
	Label textLabel;
	
	long time;
	
	public WindowMessage(String title, Skin skin) {
		super(title, skin);
		text = "";
		index = 0;
		textLabel = new Label("", WindowMgr.skin);
		
		setPosition(0, 0);
		defaults().pad(6);
		//setWidth(200);
		//row().fill().pad(6);
		row().fill().expand(true, true);
		
		textLabel.setAlignment(Align.left | Align.top);
		add(textLabel);
		//row();
		
		setTitleAlignment(Align.left);
	}
	
	public void startText(String text){
		this.text = text;
		textLabel.setText(text);
		this.pack();
		textLabel.setText("");
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
