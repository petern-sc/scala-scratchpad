name := "scala-scratchpad"

version := "1.0"

scalaVersion := "2.13.3"

// Overrides the "mainclass" setting in the "Compile" configuration
mainClass in Compile := Some("terraria.api.Main") //Used in Universal packageBin

// Overrides the "mainClass" setting in the "Compile" configuration, only during the "run" task
mainClass in (Compile, run) := Some("terraria.api.Main") //Used from normal sbt

val catsVersion = "2.1.1"
val catsEffectVersion = "2.0.0"
val specs2Version = "4.10.2"

libraryDependencies ++= Seq(
  "org.typelevel"           %% "cats-core"                    % catsVersion,
  "org.typelevel"           %% "cats-effect"                  % "2.1.4",
  "org.specs2"              %% "specs2-core"                  % specs2Version             % "test",
  "org.specs2"              %% "specs2-matcher-extra"         % specs2Version             % "test"
)

scalacOptions ++= Seq(
  "-Wdead-code",
  "-Werror",
  "-Wextra-implicit",
  "-Wnumeric-widen",
  "-Woctal-literal",
  "-Wunused",
  "-Wvalue-discard",
  "-Xlint",
  "-deprecation",
  "-feature",
  "-language:higherKinds",
  "-unchecked"
)

scalacOptions in Test --= Seq(
  "-Ywarn-value-discard",
  "-Ywarn-numeric-widen"
)

//Allows doing: > testOnly *ExampleSpec -- -ex "this is example two"
testFrameworks := Seq(TestFrameworks.Specs2)
