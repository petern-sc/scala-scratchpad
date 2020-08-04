package scratchpad.stream

import cats.effect.concurrent.Ref
import cats.effect.{Concurrent, Timer}
import cats.implicits._
import fs2.{Pipe, Stream}

import scala.concurrent.duration.FiniteDuration

object Fs2Tracker {
  // Pipe: Stream[F,A] => Stream[F,A]
  // evalTap: evalMap(x => f(x).map(_ => x))

  def apply[F[_]: Concurrent: Timer, A](
    log: Int => F[Unit],
    freq: FiniteDuration
  ): Pipe[F, A, A] = (in: Stream[F, A]) => {
    Stream.eval(Ref[F].of(0)).flatMap { count =>
      in.chunks
        .evalTap(c => count.update(_ + c.size))
        .flatMap(Stream.chunk)
        .concurrently {
          Stream.repeatEval(count.get.flatMap(log)).metered(freq)
        }
    }
  }
}
