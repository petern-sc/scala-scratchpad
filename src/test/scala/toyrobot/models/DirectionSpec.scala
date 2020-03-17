package toyrobot.models

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragments

class DirectionSpec extends Specification {
  "turnLeft" should {
    val leftTurns: List[(Direction, Direction)] = List(
      North -> West,
      East -> North,
      South -> East,
      West -> South
    )

    Fragments.foreach(leftTurns) {
      case (originalDirection, resultDirection) =>
        s"when starting at $originalDirection, return $resultDirection" in {
          Direction.turnLeft(originalDirection) must beEqualTo(resultDirection)
        }
    }
  }

  "turnRight" should {
    val rightTurns: List[(Direction, Direction)] = List(
      North -> East,
      East -> South,
      South -> West,
      West -> North
    )

    Fragments.foreach(rightTurns) {
      case (originalDirection, resultDirection) =>
        s"when starting at $originalDirection, return $resultDirection" in {
          Direction.turnRight(originalDirection) must beEqualTo(resultDirection)
        }
    }
  }
}
