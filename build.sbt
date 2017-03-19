import play.routes.compiler.InjectedRoutesGenerator
import play.sbt.PlayScala

name := """ddd-on-slick3-sample"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
//  jdbc,
//  cache,
  ws,
  "org.scalaz" %% "scalaz-core" % "7.1.4",
  "mysql" % "mysql-connector-java" % "5.1.39",
  "com.h2database" % "h2" % "1.4.192",
  "mysql" % "mysql-connector-java" % "5.1.36",
  "com.typesafe.slick" %% "slick" % "3.1.0",
  "com.typesafe.play" %% "play-slick" % "1.1.0",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.0.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "com.eaio.uuid" % "uuid" % "3.2",
  "org.scalatest" %% "scalatest" % "2.2.5" % Test,
  "org.scalatestplus" %% "play" % "1.4.0-M3" % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

flywayLocations := Seq("filesystem:conf/db/migration/h2")

flywayUrl := "jdbc:h2:file:./target/test"

flywayUser := "SA"

