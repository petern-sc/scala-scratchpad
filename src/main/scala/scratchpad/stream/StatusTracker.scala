package scratchpad.stream

import cats.effect.concurrent.Ref
import cats.effect.{ExitCode, IO, IOApp}
import fs2.Stream

import scala.concurrent.duration._

object StatusTracker extends IOApp {
  val stream: Stream[IO, Int] = Stream.range(1, 10).covary[IO].metered(1.second)

  val program: IO[Stream[IO, Int]] = for {
    ref <- Ref.of[IO, Int](0)

    transformed = stream.evalMap(item => incrementRef(ref).map(_ => item))

    loggerStream = Stream.awakeEvery[IO](5.second).evalMap(_ => logStatus(ref))
  } yield transformed.concurrently(loggerStream)


  private def incrementRef(ref: Ref[IO, Int]): IO[Int] = {
    ref.modify(x => (x + 1, x))
  }

  private def logStatus(ref: Ref[IO, Int]): IO[Unit] = {
    for {
      count <- ref.get
      _ <- IO(println(count))
      _ <- ref.set(0)
    } yield ()
  }

  override def run(args: List[String]): IO[ExitCode] = {
    program.flatMap(_.compile.drain).map(_ => ExitCode.Success)
  }
}


//  private def transform(i: Int): Either[String, Int] = {
//    if (i % 2 == 0)
//      Right(i)
//    else
//      Left(s"$i is not even")
//  }
