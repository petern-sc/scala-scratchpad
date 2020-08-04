package terraria.api

import cats.effect.{ExitCode, IO}
import cats.implicits.catsSyntaxFlatMapOps

object AppRuntime {
  def run(): IO[ExitCode] = {
    IO(println("hello world")) >> IO(ExitCode.Success)
  }
}
