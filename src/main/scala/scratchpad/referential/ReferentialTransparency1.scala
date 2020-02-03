package scratchpad.referential

import scala.io.StdIn._

object ReferentialTransparency1 extends App {
  def original(): Int = {
    val first = readInt() // 2
    val second = readInt() // 3

    first + second // 5
  }

  def refactored(): Int = {
    val getNumber = readInt() // 2

    val first = getNumber
    val second = getNumber

    first + second // 4
  }

  println(refactored())
}
