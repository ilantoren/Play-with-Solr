import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "solr-mongo-angular"
  val appVersion      = "0.1-SNAPSHOT"

  val appDependencies = Seq(
    cache,
    "com.google.inject" % "guice" % "3.0",
    "javax.inject" % "javax.inject" % "1",
    "org.reactivemongo" %% "reactivemongo" % "0.10.0",
    "org.reactivemongo" %% "play2-reactivemongo" % "0.10.2",

    "org.mockito" % "mockito-core" % "1.9.5" % "test"
  )

  
  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
