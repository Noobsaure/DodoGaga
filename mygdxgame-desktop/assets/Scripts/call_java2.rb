require "java"

java_import "com.me.mygdxgame.utils.script.IGameObject"


class CallJava

  alias updateRedefini update
  def update
	updateRedefini
	puts @x
  end
  
  
end
