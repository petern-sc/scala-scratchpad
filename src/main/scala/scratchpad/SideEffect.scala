package scratchpad

import cats.data.Writer
import cats.effect.IO

object SideEffect {
  def sideEffect(): Unit = println("Hello")

  def signalSideEffect(): IO[Unit] = IO(println("launch missiles"))

//  def free(): Free[AppAction, User] = ???

  def writer(): Writer[String, Unit] = ???

//  def getUser()[R :_getUser]: Eff[R, User] = ???
}

// Not signalling side effects
// Not telling you where things can go wrong
// Not telling you what potential effects are
