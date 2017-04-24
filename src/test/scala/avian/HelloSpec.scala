package com.avian

import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
  "The Entry object" should "running" in {
    Entry.url shouldEqual "someurl"
  }
}
