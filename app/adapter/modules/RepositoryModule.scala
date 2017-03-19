package adapter.modules

import com.google.inject.{ AbstractModule, TypeLiteral }

import adapter.ioGateway.rds.slick.DBIOTaskRunnerImpl
import adapter.ioGateway.repository.user.UserRepositoryImpl
import slick.dbio.DBIO
import util.dddSupport.adapter.DBIOTaskRunner

import domainInterface.adapter.ioGateway.UserRepository

class RepositoryModule extends AbstractModule {

  def configure(): Unit = {
    bind(new TypeLiteral[DBIOTaskRunner[DBIO]]() {}).to(classOf[DBIOTaskRunnerImpl])
    bind(new TypeLiteral[UserRepository[DBIO]]() {}).to(classOf[UserRepositoryImpl])
  }
}
