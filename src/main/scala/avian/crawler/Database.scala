package com.avian.crawler.database

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import com.avian.utils.config.Configs
import com.avian.crawler.types.Onion
import com.mongodb.casbah.Imports._

/* @Desc: Actor that works on the database document insertion
 * @Todo: Parsing object */
class DatabasePostActor extends Actor
        with Creation
        with Updating
        with Deleting {
    def receive = {
        case msg: String => msg match {
            case "hello" => sender ! "hello"
        }
        case _ => println("Command not implented")
    }
}

/* @Desc: Actor that works on the database document searching
 * @Todo: Parsing onject */
class DatabaseGetActor extends Actor
        with Reading {
    def receive = {
        case msg: String => msg match {
            case "hello" => sender ! "hello"
        }
        case _ => println("Command not implented")
    }
}

/* @Desc: Scala object to pass running 'pool' to traits
 * @Todo: Make factory more consitent by using extensions for database actors
 */
object MongoFactory {
   val mongoClient = MongoClient(
        "localhost",
        27017
    )
    val database = mongoClient("avian")
    val collection = database("crawled")
}

/* @Desc: Actors interface that implement CRUD procedure
 * @Note: Only traits "MUST" interact with mongo factory Method */
trait Creation {
    /* Pass a Onion object, and store in the database */
    def insertObject(onion: Onion) = {
        val obj = Common.buildObjectToMongo(onion)
        /* Collection is refered as collection @MongoFactory */
        MongoFactory.collection.save(obj)
    }
}

trait Reading {
    /* Search in the database for Key Values, by returning Onion object
       if matching values are correct */
    def searchKv(k: String, v: String): Onion = {
        val query = MongoDBObject(k -> v)
        val res = MongoFactory.collection.findOne(query)
        res match {
            case Some(e) => Common.buildMongoToObject(e)
            case None => Onion("", false) /* Return a nil object */
        }
    }

    def countAll(): Int = {
        /* Return trivialy the number of objects in the database */
        MongoFactory.collection.count()
    }
}

trait Updating {
    /* Update corresponding hash values */
    def updateOnion(hash: String, onion: Onion): Boolean = {
        val query = MongoDBObject("hash" -> hash)
        MongoFactory.collection.findAndModify(query,
            Common.buildObjectToMongo(onion)) match {
            case Some(e) => true
            case None => false
        }
    }
}

trait Deleting {
    /* Delete object by using corresponding kv pair */
    def delete(k: String, v: String): Boolean = {
        val query = MongoDBObject(k -> v)
        MongoFactory.collection.findAndRemove(query) match {
            case Some(e) => true
            case None => false
        }
    }
}

/* @Desc: Small utils related to the database
*/
object Common {
    /* @Desc: Convert Onion object to mongodb BSON's format type
     */  
    def buildObjectToMongo(onion: Onion): MongoDBObject = {
        val builder = MongoDBObject.newBuilder
        builder += "hash" -> onion.hash
        builder += "active" -> onion.active
        builder.result /* Return BSON object */
    }
    /* @Desc: Convert mongodb BSON's format type to Onion object
     */
    def buildMongoToObject(obj: MongoDBObject): Onion = {
        val hash = obj.getAs[String]("hash").get
        val active = obj.getAs[Boolean]("active").get
        Onion(hash, active)
    }
}
