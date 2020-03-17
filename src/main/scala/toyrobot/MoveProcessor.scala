package toyrobot

import toyrobot.models._

object MoveProcessor {
  def apply(currentPosition: Position): Position = {
    currentPosition.direction match {
      case North => currentPosition.copy(y = currentPosition.y + 1)
      case East => currentPosition.copy(x = currentPosition.x + 1)
      case South => currentPosition.copy(y = currentPosition.y - 1)
      case West => currentPosition.copy(x = currentPosition.x - 1)
    }
  }
}
