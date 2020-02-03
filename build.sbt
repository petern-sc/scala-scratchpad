name := "scala-scratchpad"

version := "1.0"

scalaVersion := "2.13.1"

// Overrides the "mainclass" setting in the "Compile" configuration
mainClass in Compile := Some("com.reagroup.scalascratchpad.Main") //Used in Universal packageBin

// Overrides the "mainClass" setting in the "Compile" configuration, only during the "run" task
//mainClass in (Compile, run) := Some("com.reagroup.scalascratchpad.Dev") //Used from normal sbt

val catsVersion = "2.1.0"
val catsEffectVersion = "2.0.0"
val fs2Version = "2.2.1"
val specs2Version = "4.8.3"

libraryDependencies ++= Seq(
  "org.typelevel"           %% "cats-core"                    % catsVersion,
  "org.typelevel"           %% "cats-free"                    % catsVersion,
  "org.typelevel"           %% "cats-effect"                  % catsEffectVersion,
  "co.fs2"                  %% "fs2-core"                     % fs2Version,
  "org.specs2"              %% "specs2-core"                  % specs2Version             % "test",
  "org.specs2"              %% "specs2-matcher-extra"         % specs2Version             % "test"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-language:higherKinds",
  "-Xfatal-warnings"
)

scalacOptions in Test --= Seq(
  "-Ywarn-value-discard",
  "-Ywarn-numeric-widen"
)

//Allows doing: > testOnly *ExampleSpec -- -ex "this is example two"
testFrameworks := Seq(TestFrameworks.Specs2)
