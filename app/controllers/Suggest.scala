package controllers
import play.api.mvc.{Request, Controller, Action}
import play.api.libs.json.{JsValue, Json, JsString}
import javax.inject.Singleton
import scala.concurrent.{ExecutionContext, Future, Awaitable, Await}
import scala.concurrent.duration.Duration
import play.api.libs.ws.{Response, WS}
import play.api.cache.Cache
import play.api.Play.current
import play.api.Logger
import scala.util.{Failure, Success}

/**
 * Created by Owner on 4/27/14.
 */
@Singleton
class Suggest  extends Controller {

   val logger  = Logger.underlyingLogger


  def find(query: String) = Action {
    implicit request =>
          var itselfJsonString = ""

          val cachevalue = Cache.getAs[String](query)

          cachevalue match {
            case None   => itselfJsonString = ""
            case Some(_)   => itselfJsonString = cachevalue.get
          }

        if ( itselfJsonString.isEmpty) {
          val  itselfJson = getSolr(query)
          if ( itselfJson != null ) {
            //Use the first Await after the last future
            Cache.set(query, Json.stringify(itselfJson), 60 * 60)
            logger.info("to cache " + query)
          }
          Ok ( itselfJson )
          }
           else {
            logger.debug( itselfJsonString )
            val itselfJson = Json.parse(itselfJsonString)
            logger.info( "sent from cache " + query )
            Ok( itselfJson)
          }
  }


  def getSolr( key: String )  : JsValue = {
    import ExecutionContext.Implicits.global

    val value :Future[JsValue] = Statix.doParams(Statix.SolrSelectWSReq,
        List(
          "wt" -> "json",
          "q" -> key,
          "indent" -> "true",
          "fl" -> "textsuggest, id"
        )).get().map( response=> (response.json\"response"\"docs"))

      value onComplete {
       case Success(x) => {
         logger.debug("After Complete")
         return x
       }
       case Failure(_) => logger.error("Failed")
      }

     return Await.result( value , Duration("1 sec"))
  }





}
object Statix { //Noder must extend this
    def SolrSelectWSReq = WS.url("http://127.0.0.1:8983/solr/mfe/browse/")
    def doParams(request: WS.WSRequestHolder, params: List[(String, String)]) = {
        params.foldLeft( request){
            (wsReq, tuple) => wsReq.withQueryString( tuple)}}
}
