package com.avian

import org.scalatest._
import com.avian.utils.config._
  
class ConfigTest extends FlatSpec with Matchers {
    "The config" should "be parsed and formated in a string" in {
        lazy val instance = Configs.instance
        lazy val mongohost = Configs.mongo("host")
        val finalstring = f"The instance name is $instance and the mongo host is $mongohost."

        finalstring should be ("The instance name is avian and the mongo host is localhost.")
    }
}
