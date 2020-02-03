package scratchpad.referential

import cats.effect.IO

import scala.io.StdIn._

object ReferentialTransparency2 extends App {
  def original(): IO[Int] = {
    for {
      first <- IO(readInt()) // 2
      second <- IO(readInt()) // 3
    } yield first + second // 5
  }

  def refactored(): IO[Int] = {
    val getNumber = IO(readInt())

    for {
      first <- getNumber // 2
      second <- getNumber // 3
    } yield first + second // 5
  }

  println(refactored().unsafeRunSync())
}

// do future next
// real life example
