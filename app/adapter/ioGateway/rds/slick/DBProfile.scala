package adapter.ioGateway.rds.slick

import com.typesafe.config.ConfigFactory

import slick.driver.JdbcProfile
import play.api.Play
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfig }

trait DBProfile extends HasDatabaseConfig[JdbcProfile] {

  val masterDB = db

  private lazy val masterDBName: Option[String] = {
    val key = "play.slick.dbName"
    val config = ConfigFactory.load
    if (config.hasPath(key)) Option(config.getString(key)).filterNot(_.isEmpty)
    else None
  }

  override protected[this] lazy val dbConfig = masterDBName match {
    case Some(name) => DatabaseConfigProvider.get[JdbcProfile](name)(Play.current)
    case _ => DatabaseConfigProvider.get[JdbcProfile](Play.current)
  }

  protected[this] lazy val masterJodaSupport = masterDBName match {
    case Some(name) if name.equalsIgnoreCase("h2") => com.github.tototoshi.slick.H2JodaSupport
    case _ => com.github.tototoshi.slick.MySQLJodaSupport
  }

}

