import sbt._

object Dependencies {
  /* Versions
   */
  lazy val akkaVersion = "2.5.0"
  lazy val testVersion = "3.0.1"

  /* Libraries
   */
  val scalaTest = "org.scalatest" %% "scalatest" % testVersion
  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  
  val backendDeps = Seq(akkaActor, scalaTest)
}
