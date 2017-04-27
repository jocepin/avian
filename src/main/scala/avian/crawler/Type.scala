package com.avian.crawler.types

/* @Desc: Object to store crawled values and then
 * convert it ton BSON for mongodb actors.
 */
case class Onion(
    hash: String,
    active: Boolean,
)
