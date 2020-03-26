package scratchpad.stream

import monix.eval.Task
import monix.execution.Scheduler
import monix.reactive.Observable

import scala.concurrent.duration._

object MonixTracker {
  def apply[A](
    logProcess: Int => Task[Unit],
    frequency: FiniteDuration
  )(implicit scheduler: Scheduler): Observable[A] => Observable[A] =
    (observable: Observable[A]) => {
      observable
        .bufferTimed(frequency)
        .mapEval { seq =>
          logProcess(seq.length).map(_ => seq)
        }
        .flatMap(seq => Observable.fromIterable(seq))
    }
}
