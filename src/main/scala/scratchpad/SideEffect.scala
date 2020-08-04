package scratchpad

import cats.data.Writer
import cats.effect.IO

object SideEffect {
  sealed trait AppAction
  type User = String

  def sideEffect(): Unit = println("Hello")

  def signalSideEffect(): IO[Unit] = IO(println("launch missiles"))

  val a: IO[Double] = IO(Math.random())
  val b: Double = Math.random()

//  def free(): Free[AppAction, User] = ???

  def writer(): Writer[String, Unit] = ???

//  def getUser()[R :_getUser]: Eff[R, User] = ???
}

// Not signalling side effects
// Not telling you where things can go wrong
// Not telling you what potential effects are
