package com.avian

import org.scalatest._
import com.avian.crawler.database._
import akka.actor.ActorSystem
import akka.actor.Actor
import akka.testkit.{TestKit, TestActorRef}
import com.mongodb.casbah.Imports._

class DatabaseTest extends FlatSpec with Matchers {
    "The factory" should "work properly" in {
        val test = MongoFactory
        test.mongoClient should be (MongoFactory.mongoClient)
    }
}
