package controllers

import play.api.mvc.{Controller, Action}
import reactivemongo.api.Cursor
import play.api.libs.json.{JsArray, Json}
import scala.concurrent.{ExecutionContext, Future}
import javax.inject.Singleton
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection
import play.api.Logger

/**
 * Created by Owner on 4/30/14.
 */
@Singleton
class Places  extends Controller with MongoController {

   val logger  = Logger.underlyingLogger

  /*
   * Get a JSONCollection (a Collection implementation that is designed to work
   * with JsObject, Reads and Writes.)
   * Note that the `collection` is not a `val`, but a `def`. We do _not_ store
   * the collection reference to avoid potential problems in development with
   * Play hot-reloading.
   */
  def collection: JSONCollection = db.collection[JSONCollection]("places")

  import models.{Place}
  import models.JsonFormats._
  import ExecutionContext.Implicits.global

  def find(locId : String ) = Action.async {
    // let's do our query

    logger.info( "Searching for place with id " + locId )

    val cursor: Cursor[Place] = collection.
      // find all
      find(Json.obj("cid" -> locId)).cursor[Place]
     logger.info( "gather all the JsObjects in a list")
    // gather all the JsObjects in a list
    val futurePlaceList: Future[List[Place]] = cursor.collect[List]()

    logger.info ("transform the list into a JsArray")
    // transform the list into a JsArray
    val futurePersonsJsonArray: Future[JsArray] = futurePlaceList.map { places =>
      Json.arr(places)
    }

    logger.info ("DONE transform the list into a JsArray")

    // everything's ok! Let's reply with the array
    futurePersonsJsonArray.map {
      places => {
        logger.info( Json.stringify(places))
        Ok( places(0) )
        }
    }
  }
}
