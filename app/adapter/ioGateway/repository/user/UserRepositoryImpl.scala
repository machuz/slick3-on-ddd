package adapter.ioGateway.repository.user

import adapter.ioGateway.rds.slick.dao.UsersDAO
import slick.dbio.DBIO

import scala.concurrent.ExecutionContext.Implicits.global

import domain.model.user.{ User, UserId }
import domainInterface.adapter.ioGateway.UserRepository

class UserRepositoryImpl
  extends UserRepository[DBIO]
  with UserConverter {

  override def resolveById(id: UserId): DBIO[Option[User]] = {
    for {
      res <- UsersDAO.ioFindById(id.value)
    } yield res.map(convertToDomainModel)
  }

  override def resolveAll: DBIO[Seq[User]] = {
    for {
      res <- UsersDAO.ioFindAll
    } yield res.map(convertToDomainModel)
  }

  override def update(entity: User): DBIO[Option[User]] = {
    for {
      user <- UsersDAO.ioPut(convertToDataModel(entity))
    } yield user.map(convertToDomainModel)
  }

  override def store(entity: User): DBIO[User] = {
    for {
      res <- UsersDAO.ioAdd(convertToDataModel(entity))
    } yield convertToDomainModel(res)
  }

  override def deleteById(id: UserId): DBIO[Boolean] = {
    UsersDAO.ioRemoveById(id.value)
  }

  override type This = this.type
}
