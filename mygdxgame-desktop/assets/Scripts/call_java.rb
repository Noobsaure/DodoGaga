require "java"

java_import "com.me.mygdxgame.utils.script.IGameObject"


class CallJava
  include IGameObject
  
  def initialize
    super
	@x = 0
  end

  def setup(sprite)
	@sprite = sprite
  end
  
  def update
	@x += 1
	@sprite.setPosition(@x, 0, 500)
  end
  
  
end
