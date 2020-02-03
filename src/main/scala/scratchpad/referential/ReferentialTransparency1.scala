package scratchpad.referential

import scala.io.StdIn._

object ReferentialTransparency1 extends App {
  def original(): Int = {
    val first = readInt()
    val second = readInt()

    first + second
  }

  def refactored(): Int = {
    val getNumber = readInt()

    val first = getNumber
    val second = getNumber

    first + second
  }

  println(refactored())
}
