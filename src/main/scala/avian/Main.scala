package com.avian

/* @info: Application entrypoint
 */
object Entry extends Config with App {
  println("The bot is running")
}

trait Config {
  lazy val url: String = "someurl"
}
