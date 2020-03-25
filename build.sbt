name := "scala-scratchpad"

version := "1.0"

scalaVersion := "2.13.1"

// Overrides the "mainclass" setting in the "Compile" configuration
mainClass in Compile := Some("scalascratchpad.Main") //Used in Universal packageBin

// Overrides the "mainClass" setting in the "Compile" configuration, only during the "run" task
mainClass in (Compile, run) := Some("toyrobot.Main") //Used from normal sbt

val catsVersion = "2.1.0"
val catsEffectVersion = "2.0.0"
val specs2Version = "4.8.3"

libraryDependencies ++= Seq(
  "org.typelevel"           %% "cats-core"                    % catsVersion,
  "org.typelevel"           %% "cats-free"                    % catsVersion,
  "org.typelevel"           %% "cats-effect"                  % catsEffectVersion,
  "co.fs2"                  %% "fs2-core"                     % "2.2.1",
  "io.monix"                %% "monix"                        % "3.1.0",
  "org.specs2"              %% "specs2-core"                  % specs2Version             % "test",
  "org.specs2"              %% "specs2-matcher-extra"         % specs2Version             % "test"
)

scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
  "-language:experimental.macros", // Allow macro definition (besides implementation and application)
  "-language:higherKinds", // Allow higher-kinded types
  "-language:implicitConversions", // Allow definition of implicit functions called views
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.
  "-Xfatal-warnings", // Fail the compilation if there are any warnings.
  "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Xlint:constant", // Evaluation of a constant arithmetic expression results in an error.
  "-Xlint:delayedinit-select", // Selecting member of DelayedInit.
  "-Xlint:doc-detached", // A Scaladoc comment appears to be detached from its element.
  "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
  "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`.
  "-Xlint:missing-interpolator", // A string literal appears to be missing an interpolator id.
  "-Xlint:nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
  "-Xlint:option-implicit", // Option.apply used implicit view.
  "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
  "-Xlint:private-shadow", // A private field (or class parameter) shadows a superclass field.
  "-Xlint:stars-align", // Pattern sequence wildcard must align with sequence component.
  "-Xlint:type-parameter-shadow", // A local type parameter shadows a type already in scope.
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-extra-implicit", // Warn when more than one implicit parameter section is defined.
  "-Ywarn-numeric-widen", // Warn when numerics are widened.
  "-Ywarn-value-discard", // Warn when non-Unit expression results are unused.
  "-Ybackend-parallelism", "8", // Enable paralellisation — change to desired number!
  "-Ycache-plugin-class-loader:last-modified", // Enables caching of classloaders for compiler plugins
  "-Ycache-macro-class-loader:last-modified", // and macro definitions. This can lead to performance improvements.
)

scalacOptions in Test --= Seq(
  "-Ywarn-value-discard",
  "-Ywarn-numeric-widen"
)

//Allows doing: > testOnly *ExampleSpec -- -ex "this is example two"
testFrameworks := Seq(TestFrameworks.Specs2)
