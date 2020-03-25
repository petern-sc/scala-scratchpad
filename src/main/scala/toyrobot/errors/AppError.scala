package toyrobot.errors

sealed trait AppError extends Product with Serializable

object AppError {
  type ErrorOr[A] = Either[AppError, A]

  case object InvalidCommand extends AppError
  case object InvalidDirection extends AppError
  case object InvalidCoordinate extends AppError
  case object RobotFallenOff extends AppError
}
