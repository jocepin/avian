package com.avian.crawler.utils

/* Url related methods
*/
object Url {
    /* @Desc: Extract onion hash from url
     * @Todo: Write a better regex to avoid suffix stripping
     */
    def extractHash(url: String): String = {
        /* Return an instance from the regex class */
        val pattern = """(?:https?://)?(?:www\.)?([A-Za-z0-9._%+-]+)/?.*""".r
        url match {
            case pattern(domain) => domain.stripSuffix(".onion").trim
        }
    }

    /* @Desc: Create .onion TLD url by providing public key base32 hash
     * @Todo: Improve ssl parameter and better error parsing
     */
    def makeUrl(hash: String, ssl: Boolean): String = {
        if(ssl) {
            checkValidHash(hash) match {
                case true => "https://%s.onion".format(hash)
                case _ => ""
            }
        } else {
            checkValidHash(hash) match {
                case true => "http://%s.onion".format(hash)
                case _ => ""
            }
        }
    }

    /* @Desc: Predicate to check if the hash length is correct
     * @Todo: Check if the hash is base32
     */
    def checkValidHash(hash: String): Boolean = hash.length match {
        case 16 => true
        case _  => false
    }
}
