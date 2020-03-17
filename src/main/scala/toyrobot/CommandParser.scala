package toyrobot

import toyrobot.errors.AppError.{ErrorOr, InvalidCommand, InvalidCoordinate, InvalidDirection}
import toyrobot.errors.{AppError, AppTry}
import toyrobot.models.Command.{Move, Place}
import toyrobot.models._
import cats.implicits._

import scala.util.matching.Regex

object CommandParser {

  def parseCommand(rawCommand: String): ErrorOr[Command] = {
    rawCommand.toUpperCase match {
      case "MOVE" => Right(Move)
      case "LEFT" => Right(Command.Left)
      case "RIGHT" => Right(Command.Right)
      case "REPORT" => Right(Command.Report)
      case placeRegex(x, y, direction) => parsePlaceCommand(x, y, direction)
      case _ => Left(InvalidCommand)
    }
  }

  // REGEX: place (any string),(any string),(any string)
  // Could entirely validate with regex, but this gives more precise errors
  private val placeRegex: Regex = """(?i)place\s(.*),(.*),(.*)""".r

  private def parsePlaceCommand(x: String, y: String, direction: String): ErrorOr[Command] = {
    val errorOrDirection: ErrorOr[Direction] = direction.toUpperCase match {
      case "NORTH" => Right(North)
      case "EAST" => Right(East)
      case "SOUTH" => Right(South)
      case "WEST" => Right(West)
      case _ => Left(InvalidDirection)
    }

    val errorOrX: ErrorOr[Int] = AppTry(x.toInt).leftMap(_ => InvalidCoordinate)
    val errorOrY: ErrorOr[Int] = AppTry(y.toInt).leftMap(_ => InvalidCoordinate)

    for {
      direction <- errorOrDirection
      x <- errorOrX
      y <- errorOrY
    } yield Place(x, y, direction)
  }
}
