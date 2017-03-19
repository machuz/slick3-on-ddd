package usecase.user

import com.google.inject.Inject

import util.dddSupport.adapter.DBIOTaskRunner

import scala.concurrent.{ ExecutionContext, Future }
import scala.language.higherKinds

import scalaz.Monad
import scalaz.Scalaz._

import domain.model.user.User
import domainInterface.adapter.ioGateway.UserRepository
import domainInterface.usecase.user.CreateUserUseCase

class CreateUserUseCaseImpl[M[+_]] @Inject()(
  implicit monad: Monad[M],
  userRepository: UserRepository[M],
  taskRunner: DBIOTaskRunner[M]) extends CreateUserUseCase {

  override def call(arg: String)(implicit ec: ExecutionContext): Future[User] = {
    val name = arg
    taskRunner.runTransaction(for {
      user1 <- userRepository.store(User(name = name))
//      user2 <- userRepository.store(User(name = name))
    } yield user1)
  }
}
