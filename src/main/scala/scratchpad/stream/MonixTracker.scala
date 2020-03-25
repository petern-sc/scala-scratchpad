package scratchpad.stream

import monix.eval.Task
import monix.execution.Scheduler
import monix.reactive.Observable

import scala.concurrent.duration._

object MonixTracker {
  def apply[A](observable: Observable[A])(implicit scheduler: Scheduler): Observable[A] = {
    implicit val s: Scheduler = scheduler

    observable
      .bufferTimed(FiniteDuration(5, SECONDS))
      .mapEval { seq => logProcess[A](seq.length, seq) }
      .flatMap(seq => Observable.fromIterable(seq))
  }

  private def logProcess[A](numberProcessed: Int, seq: Seq[A]): Task[Seq[A]] = Task {
    println(s"Number of lines processed in the last 30 seconds: $numberProcessed")
    seq
  }

}
