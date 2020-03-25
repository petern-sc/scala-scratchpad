package toyrobot

import cats.effect.IO
import cats.implicits._
import toyrobot.errors.AppError.{ErrorOr, RobotFallenOff}
import toyrobot.models._

class Program(
  reportPosition: Position => IO[Unit],
  boardMaxX: Int,
  boardMaxY: Int
) {
  def handleCommand(
    currentPosition: Position,
    command: Command
  ): IO[ErrorOr[Position]] = {
    val newPosition: IO[Position] = (command match {
      case Command.Report => reportPosition(currentPosition)
      case _ => IO.unit
    }).map(_ => MoveProcessor(currentPosition, command))

    val errorOrValidPosition: IO[ErrorOr[Position]] = newPosition.map(validateCoordinate)
    errorOrValidPosition
  }

  private def validateCoordinate(newPosition: Position): ErrorOr[Position] = {
    val errorOrValidX =
      if (newPosition.x < 0 || newPosition.x > boardMaxX)
        Left(RobotFallenOff)
      else
        Right(newPosition.x)

    val errorOrValidY =
      if (newPosition.y < 0 || newPosition.y > boardMaxY)
        Left(RobotFallenOff)
      else
        Right(newPosition.y)

    (errorOrValidX *> errorOrValidY).map(_ => newPosition)
  }
}
