package com.avian

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import com.avian.crawler.database.{DatabasePostActor, DatabaseGetActor}

/* @Desc: Application entrypoint
 */
object Entry extends App {
    /* @Todo: Initialize actors properly
     

    /* Create actor superviser */
    val system = ActorSystem("AvianSystem")

    /* Create database actor */
    val dbPost = system.actorOf(Props[DatabasePostActor], name = "dbPost")
    val dbGet  = system.actorOf(Props[DatabaseGetActor], name = "dbGet")

    /* Pass some test values to actors */
    dbPost ! "foo"
    dbPost ! "bar"*/
}
