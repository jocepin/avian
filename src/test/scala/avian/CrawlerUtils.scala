package com.avian

import org.scalatest._
import com.avian.crawler.utils._

class UtilsTest extends FlatSpec with Matchers {
    "A onion hash" should "have a correct length" in {
        Url.checkValidHash("3g2upl4pq6kufc4m") should be (true)
        Url.checkValidHash("s0m3f001nv4l1dh4sh") should be (false)
    }
    "A onion url" should "be extracted as string hash" in {
        Url.extractHash("http://3g2upl4pq6kufc4m.onion/") should be ("3g2upl4pq6kufc4m")
    }
    "A onion hash" should "be forged as non-SSL url" in {
        Url.makeUrl("3g2upl4pq6kufc4m", false) should be ("http://3g2upl4pq6kufc4m.onion")
        Url.makeUrl("s0m3f001nv4l1dh4sh",false) should be ("")
    }
    "A onion hash" should "be forged as SSL url" in {
        Url.makeUrl("3g2upl4pq6kufc4m", true) should be ("https://3g2upl4pq6kufc4m.onion")
        Url.makeUrl("s0m3f001nv4l1dh4sh", true) should be ("")
    }
}
