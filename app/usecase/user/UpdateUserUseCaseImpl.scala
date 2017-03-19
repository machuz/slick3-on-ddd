package usecase.user

import com.google.inject.Inject

import util.dddSupport.adapter.DBIOTaskRunner

import scala.concurrent.{ ExecutionContext, Future }
import scala.language.higherKinds

import scalaz.Monad
import scalaz.Scalaz._

import domain.model.user.{ User, UserId }
import domainInterface.adapter.ioGateway.UserRepository
import domainInterface.usecase.user.UpdateUserUseCase

class UpdateUserUseCaseImpl[M[+ _]] @Inject()(
  implicit monad: Monad[M],
  userRepository: UserRepository[M],
  taskRunner: DBIOTaskRunner[M]
) extends UpdateUserUseCase[M] {

  override def call(arg: (UserId, String))(implicit ec: ExecutionContext): Future[Option[User]] = {
    val (userId, name) = arg
    taskRunner.runTransaction(
      for {
        userOpt <- userRepository.resolveById(userId)
        res <- {
          userOpt match {
            case Some(x) => userRepository.update(x.copy(name = name))
            case _ => none.point[M]
          }
        }
      } yield res)
  }
}
