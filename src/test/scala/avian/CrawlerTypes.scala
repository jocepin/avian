package com.avian

import org.scalatest._
import com.avian.crawler.types._

class TypeTest extends FlatSpec with Matchers {
    "The Onion object" should "be correctly parsed" in {
        val foo = Onion("testhash",true)
        val f = "The %s should be %s".format(foo.hash,foo.active) should be ("The testhash should be true")
    }
}
