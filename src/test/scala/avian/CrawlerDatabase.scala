package com.avian

import org.scalatest._
import com.avian.crawler.database._
import com.avian.crawler.types.Onion
import akka.actor.ActorSystem
import akka.actor.Actor
import akka.testkit.{TestKit, TestActorRef, ImplicitSender}
import com.mongodb.casbah.Imports._

class DatabaseTest extends FlatSpec with Matchers {

    "The factory" should "work properly" in {
        val test = MongoFactory
        test.mongoClient should be (MongoFactory.mongoClient)
    }

    "The Common object utils" should "build type properly" in {
        val foo = Onion("somefakehash",true) // Onion type
        val bar = Common.buildObjectToMongo(foo) // Mongo type
        Common.buildObjectToMongo(foo) should be (bar)
        Common.buildMongoToObject(bar) should be (foo)
 
    }

    "The CRUD actions" should "work properly" in {
        class TestDb extends Creation
                with Reading
                with Updating
                with Deleting {}

        val quux = new TestDb
        val foo = Onion("somefakehash",true) // Onion type
        val bar = Common.buildObjectToMongo(foo) // Mongo type

        /* Insert */
        quux.insertObject(foo)

        /* Query */
        quux.searchKv("hash","somefakehash") should be (Onion("somefakehash",true))
        quux.searchKv("hash","thishashdontexits") should be (Onion("",false))
        quux.countAll().getClass should be (3.getClass)

        /* Update */
        quux.updateOnion("buggy hash name",foo) should be (false) // Not found corresponding hash
        quux.updateOnion("somefakehash",foo) should be (true) // Update exisiting record

        /* Delete*/
        quux.delete("hash","somefakehash") should be (true) // Hash found
        quux.delete("buggyhash","somenotfoundhash") should be (false) // Key not found
    }
}


class BasicTest extends TestKit(ActorSystem("testSystem"))
        with ImplicitSender
        with Matchers {

    /*"A simple actor" must {

        " (1) receive message" in {
            val dbPost = TestActorRef[DatabasePostActor]
            dbPost ! "hello"
            expectMsg("hello")
        }

        " (2) receive message" in {
            val dbGet = TestActorRef[DatabaseGetActor]
            expectMsg("test")
        }
    }*/
}
