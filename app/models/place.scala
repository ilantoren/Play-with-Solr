package models

/**
 * Created by Owner on 4/30/14.
 */
  case class Place( cid: String,
                   textsuggest: String,
                   url : String,
                   subtext: String,
                   typestr: String,
                   thumbnail_url: String
                      )

object JsonFormats {
  import play.api.libs.json.Json

  // Generates Writes and Reads for Feed and User thanks to Json Macros
  implicit val userFormat = Json.format[User]
  implicit val  placeFormat = Json.format[Place]
}
