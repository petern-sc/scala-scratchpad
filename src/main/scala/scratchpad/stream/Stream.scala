package scratchpad.stream

import cats.effect.ExitCode
import cats.implicits._
import monix.eval.{Task, TaskApp}
import monix.execution.Scheduler
import monix.reactive.Observable

object Stream extends TaskApp {
  implicit private val s: Scheduler = scheduler

  override def run(args: List[String]): Task[ExitCode] = {
    // Prints 1..100 with a 1 second delay
    val monixStream: Observable[Unit] = Observable
      .range(0, 10)
      .map(_.toInt)
      .mapEval(i => Task(Thread.sleep(1000)).map(_ => i))
      .mapEval(i => Task(println(i)))

    val streamWithTracking: Observable[Unit] = MonixTracker(monixStream)

    val x = streamWithTracking.mapEval(_ => Task(println("more logging")))

    // Run the stream
    x.completedL *> Task(ExitCode.Success)
  }
}
