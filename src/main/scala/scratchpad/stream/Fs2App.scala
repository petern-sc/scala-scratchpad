package scratchpad.stream

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import fs2.{Pipe, Stream}

import scala.concurrent.duration._

object Fs2App extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {

    // Prints 1..10 with a 1 second delay
    val stream = Stream
      .range(0, 10)
      .map(_.toString)
      .evalMap(i => IO(Thread.sleep(1000)).map(_ => i))
      .evalMap(i => IO(println(i)))

    val tracker: Pipe[IO, Unit, Unit] = Fs2Tracker(logProcess, 5.seconds)

    val streamWithTracking: Stream[IO, Unit] = tracker(stream)

    // Run the stream
    streamWithTracking.compile.drain *> IO(ExitCode.Success)
  }

  private def logProcess[A](numberProcessed: Int): IO[Unit] = IO {
    println(s"Number of lines processed in the last 5 seconds: $numberProcessed")
  }
}
