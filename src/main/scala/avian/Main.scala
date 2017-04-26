package com.avian

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import com.avian.crawler.database.DatabaseActor

/* @Desc: Application entrypoint
 */
object Entry extends App {
    /* @Todo: Initialize actors properly
     */

    /* Create actor superviser */
    val system = ActorSystem("AvianSystem")

    /* Create database actor */
    val dbActor = system.actorOf(Props[DatabaseActor], name = "dbActor")

    /* Pass some test values to actors */
    dbActor ! "count"
}
