package toyrobot

import toyrobot.models._

object MoveProcessor {
  def apply(currentPosition: Position, command: Command): Position = {
    command match {
      case Command.Place(x, y, direction) => Position(x, y, direction)
      case Command.Move => moveForward(currentPosition)
      case Command.Left =>
        currentPosition.copy(direction = Direction.turnLeft(currentPosition.direction))
      case Command.Right =>
        currentPosition.copy(direction = Direction.turnRight(currentPosition.direction))
      case Command.Report => currentPosition
    }
  }

  private def moveForward(currentPosition: Position): Position = {
    currentPosition.direction match {
      case North => currentPosition.copy(y = currentPosition.y + 1)
      case East => currentPosition.copy(x = currentPosition.x + 1)
      case South => currentPosition.copy(y = currentPosition.y - 1)
      case West => currentPosition.copy(x = currentPosition.x - 1)
    }
  }
}
