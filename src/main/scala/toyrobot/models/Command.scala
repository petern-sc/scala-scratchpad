package toyrobot.models

sealed trait Command extends Product with Serializable

object Command {
  case class Place(x: Int, y: Int, direction: Direction) extends Command
  case object Move extends Command
  case object Left extends Command
  case object Right extends Command
  case object Report extends Command
}
