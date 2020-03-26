package scratchpad.stream

import cats.effect.{Concurrent, Timer}
import fs2.{Pipe, Stream}

import scala.concurrent.duration.FiniteDuration

object Fs2TrackerInSteps {
  // Pipe: Stream[F,A] => Stream[F,A]
  // evalTap: evalMap(x => f(x).map(_ => x))

  def apply[F[_]: Concurrent: Timer, A](
    log: Int => F[Unit],
    freq: FiniteDuration
  ): Pipe[F, A, A] = (in: Stream[F, A]) => {

    //Create a stream from a Ref[F, Int]

    //Logging stream

    //Update the count

    ???
  }
}
