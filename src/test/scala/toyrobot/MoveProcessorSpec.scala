package toyrobot

import org.specs2.mutable.Specification
import toyrobot.models.{East, North, Position, South, West}

class MoveProcessorSpec extends Specification {
  "MoveProcessor" should {
    "move the robot forwards" in {
      "when facing North" in {
        MoveProcessor(Position(0,0,North)) must beEqualTo(Position(0,1,North))
      }
      "when facing East" in {
        MoveProcessor(Position(0,0,East)) must beEqualTo(Position(1,0,East))
      }
      "when facing South" in {
        MoveProcessor(Position(0,0,South)) must beEqualTo(Position(0,-1,South))
      }
      "when facing West" in {
        MoveProcessor(Position(0,0,West)) must beEqualTo(Position(-1,0,West))
      }
    }
  }
}
