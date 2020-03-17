package toyrobot

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragments
import toyrobot.errors.AppError
import toyrobot.errors.AppError.{InvalidCommand, InvalidCoordinate, InvalidDirection}
import toyrobot.models.{Command, East}

class CommandParserSpec extends Specification {
  "CommandParser" should {
    "parseCommand" should {
      "when given a valid command" should {
        val validCommand: List[(String, Command)] = List(
          "MOVE" -> Command.Move,
          "LEFT" -> Command.Left,
          "RIGHT" -> Command.Right,
          "REPORT" -> Command.Report,
          "Place 1,-2,EAST" -> Command.Place(1, -2, East)
        )

        Fragments.foreach(validCommand) {
          case (rawCommand, expectedCommand) =>
            s"return $expectedCommand" in {
              CommandParser.parseCommand(rawCommand) must beRight[Command](expectedCommand)
            }
        }

        "ignore casing" in {
          CommandParser.parseCommand("MovE") must beRight[Command](Command.Move)
        }
      }

      "return error" in {
        "when given a nonsense command" in {
          CommandParser.parseCommand("not a move") must beLeft[AppError](InvalidCommand)
        }

        "when given a invalid coordinate" in {
          CommandParser.parseCommand("PLACE 1a,10,North") must beLeft[AppError](InvalidCoordinate)
        }

        "when given a invalid direction" in {
          CommandParser.parseCommand("PLACE 1,2,northerly") must beLeft[AppError](InvalidDirection)
        }
      }
    }
  }
}
