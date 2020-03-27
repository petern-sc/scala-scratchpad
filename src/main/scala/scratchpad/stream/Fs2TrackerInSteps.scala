package scratchpad.stream

import cats.effect.concurrent.Ref
import cats.effect.{Concurrent, Timer}
import fs2.{Pipe, Stream}
import cats.implicits._

import scala.concurrent.duration.FiniteDuration

object Fs2TrackerInSteps {
  // Pipe: Stream[F,A] => Stream[F,A]
  // evalTap: evalMap(x => f(x).map(_ => x))

  def apply[F[_]: Concurrent: Timer, A](
    log: Int => F[Unit],
    freq: FiniteDuration
  ): Pipe[F, A, A] = (in: Stream[F, A]) => {

    //Create a stream from a Ref[F, Int]
    Stream.eval(Ref[F].of(0))

    //Logging stream
    Stream.eval(Ref[F].of(0)).flatMap { countRef =>
      Stream.repeatEval(countRef.get.flatMap(log)).metered(freq)
    }

    //Update the count
    Stream.eval(Ref[F].of(0)).flatMap { countRef =>
      val stream1: Stream[F, A] = in.chunks
        .evalMap(chunk => countRef.update(_ + chunk.size).map(_ => chunk))
        .flatMap(Stream.chunk)

      val loggingStream: Stream[F, Unit] = Stream.repeatEval(countRef.get.flatMap(log)).metered(freq)

      stream1.concurrently(loggingStream)
    }
  }
}
