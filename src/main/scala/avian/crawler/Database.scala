package com.avian.crawler.database

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import com.avian.utils.config.Configs
import com.mongodb.casbah.Imports._

/* @Desc: Actor used to communicate with crawler actor
*/
class DatabaseActor extends Actor with MongoBuilder {    
    def receive = {
        case "count" => {
            val result = coll.count()
            sender ! println(result) /* Return a function to sender */
        }
        case _ => println("Command not implented")
    }
}

trait MongoBuilder {
    /* @Desc: Scala trait to pass running 'pool' to actor
     * @Todo: Making this trait Actor
     */
    val mongoClient = MongoClient(
        "localhost",
        27017
    )
    val db = mongoClient("avian")
    val coll = db("crawled")
}
