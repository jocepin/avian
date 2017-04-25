import sbt._

object Dependencies {
  /* Versions
   */
  lazy val akkaVersion = "2.5.0"
  lazy val testVersion = "3.0.1"

  /* Libraries
   */
  lazy val scalaTest = "org.scalatest" %% "scalatest" % testVersion
  lazy val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
}
