package com.avian.utils.config

/* @Desc: Currently storing values in this object
 * @Todo: Writing factory model to parse 
 * configurations from external files
 */
object Configs {
    lazy val instance = "avian"
    lazy val mongo = Map(
        "host" -> "localhost",
        "port" -> 27017,
        "user" -> "root",
        "password" -> "smokingkills",
        "database" -> "avian",
        "collection" -> "crawled"
    )
}
