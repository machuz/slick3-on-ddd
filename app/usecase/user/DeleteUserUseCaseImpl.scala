package usecase.user

import com.google.inject.Inject

import util.dddSupport.adapter.DBIOTaskRunner

import scala.concurrent.{ ExecutionContext, Future }
import scala.language.higherKinds

import scalaz.Monad
import scalaz.Scalaz._

import domain.model.user.UserId
import domainInterface.adapter.ioGateway.UserRepository
import domainInterface.usecase.user.DeleteUserUseCase

class DeleteUserUseCaseImpl[M[+ _]] @Inject()(
  implicit monad: Monad[M],
  userRepository: UserRepository[M],
  taskRunner: DBIOTaskRunner[M]
) extends DeleteUserUseCase {

  override def call(arg: UserId)(implicit ec: ExecutionContext): Future[Boolean] = {
    val userId = arg
    taskRunner.run(
      for {
        users <- userRepository.deleteById(userId)
      } yield users
    )
  }
}
