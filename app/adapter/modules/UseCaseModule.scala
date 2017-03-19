package adapter.modules

import com.google.inject.{ AbstractModule, TypeLiteral }

import adapter.ioGateway.rds.slick.DBIOM
import slick.dbio.DBIO
import usecase.user._

import scalaz.Monad

import domainInterface.usecase.user._

class UseCaseModule extends AbstractModule {

  def configure(): Unit = {
    bind(new TypeLiteral[Monad[DBIO]] () {}).to(classOf[DBIOM])
    bind(classOf[CreateUserUseCase]).to(new TypeLiteral[CreateUserUseCaseImpl[DBIO]]() {})
    bind(classOf[UpdateUserUseCase]).to(new TypeLiteral[UpdateUserUseCaseImpl[DBIO]]() {})
    bind(classOf[DeleteUserUseCase]).to(new TypeLiteral[DeleteUserUseCaseImpl[DBIO]]() {})
    bind(classOf[ListUserUseCase]).to(new TypeLiteral[ListUserUseCaseImpl[DBIO]]() {})
  }
}
