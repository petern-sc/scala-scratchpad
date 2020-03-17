package toyrobot.models

sealed trait Direction extends Product with Serializable

case object North extends Direction
case object East extends Direction
case object South extends Direction
case object West extends Direction

object Direction {
  def turnLeft(direction: Direction): Direction = {
    direction match {
      case North => West
      case East => North
      case South => East
      case West => South
    }
  }

  def turnRight(direction: Direction): Direction = {
    direction match {
      case North => East
      case East => South
      case South => West
      case West => North
    }
  }
}
