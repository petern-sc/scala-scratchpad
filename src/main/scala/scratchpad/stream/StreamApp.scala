package scratchpad.stream

import cats.effect.ExitCode
import cats.implicits._
import monix.eval.{Task, TaskApp}
import monix.execution.Scheduler
import monix.reactive.Observable
import scala.concurrent.duration._

object StreamApp extends TaskApp {
  implicit private val s: Scheduler = scheduler

  override def run(args: List[String]): Task[ExitCode] = {

    // Prints 1..10 with a 1 second delay
    val monixStream: Observable[Unit] = Observable
      .range(0, 10)
      .map(_.toString)
      .mapEval(i => Task(Thread.sleep(1000)).map(_ => i))
      .mapEval(i => Task(println(i)))

    val tracker: Observable[Unit] => Observable[Unit] = MonixTracker(logProcess, 5.seconds)

    val streamWithTracking: Observable[Unit] = tracker(monixStream)

    // Run the stream
    streamWithTracking.completedL *> Task(ExitCode.Success)
  }

  private def logProcess[A](numberProcessed: Int): Task[Unit] = Task {
    println(s"Number of lines processed in the last 5 seconds: $numberProcessed")
  }
}
