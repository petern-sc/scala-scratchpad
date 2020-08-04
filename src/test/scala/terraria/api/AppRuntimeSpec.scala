package terraria.api

import cats.effect.ExitCode
import org.specs2.mutable.Specification

class AppRuntimeSpec extends Specification {
  "AppRuntime" should {
    "Hello world" in {
      AppRuntime.run().unsafeRunSync() must beEqualTo(ExitCode.Success)
    }
  }
}
