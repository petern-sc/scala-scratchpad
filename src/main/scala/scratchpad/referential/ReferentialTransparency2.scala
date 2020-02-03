package scratchpad.referential

import cats.effect.IO

import scala.io.StdIn._

object ReferentialTransparency2 extends App {
  def original(): IO[Int] = {
//    val first = IO(readInt())
//    val second = IO(readInt())

    for {
      first <- IO(readInt())
      second <- IO(readInt())
    } yield first + second
  }

  def refactored(): Int = {
    val getNumber = readInt()

    val first = getNumber
    val second = getNumber

    first + second
  }

  println(original().unsafeRunSync())
}
