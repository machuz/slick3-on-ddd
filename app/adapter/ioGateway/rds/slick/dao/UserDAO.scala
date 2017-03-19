package adapter.ioGateway.rds.slick.dao

import adapter.ioGateway.rds.slick.DBProfile

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scalaz.Scalaz.{ ToOptionIdOps, none }


case class UserDataModel(
  id: String,
  name: String
)

protected[dao] trait UserComponent { self: DBProfile =>

  import driver.api._

  protected[this] val users = TableQuery[UsersTable]

  protected[this] class UsersTable(tag: Tag) extends Table[UserDataModel](tag, "users") {

    def id = column[String]("id", O.PrimaryKey)

    def name = column[String]("name")

    def * = (id, name) <> (UserDataModel.tupled, UserDataModel.unapply)
  }
}

private[ioGateway] object UsersDAO
  extends UserComponent
    with DBProfile {

  import driver.api._

  val arel = users

  def ioFindById(id: String): DBIO[Option[UserDataModel]] = arel.filter(_.id === id).result.headOption

  def ioFindAll: DBIO[Seq[UserDataModel]] = arel.result

  def ioAdd(dm: UserDataModel): DBIO[UserDataModel] = (arel += dm).map(_ => dm)

  def ioPut(dm: UserDataModel): DBIO[Option[UserDataModel]] = {
    arel.filter(_.id === dm.id).update(dm).map { a => if (a == 1) dm.some else none }
  }

  def ioRemoveById(id: String): DBIO[Boolean] = arel.filter(_.id === id).delete.map(x => x != 0)
}
