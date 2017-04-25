import Dependencies._

lazy val commonSettings = Seq(
  version := "0.1.0",
  scalaVersion := "2.12.1",
  organization := "com.avian",
  name := "avian"
)

lazy val backend = (project in file("backend"))
  .settings(
    commonSettings,
    resolvers += "Typesafe" at "http://repo.typesafe.com/typesafe/releases/",
    resolvers += "Akka Repository" at "http://repo.akka.io/releases/",
    libraryDependencies ++= backendDeps
  )
