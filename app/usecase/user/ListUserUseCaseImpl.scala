package usecase.user

import com.google.inject.Inject

import util.dddSupport.adapter.DBIOTaskRunner

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ ExecutionContext, Future }
import scala.language.higherKinds

import scalaz.Monad
import scalaz.Scalaz._

import domain.model.user.User
import domainInterface.adapter.ioGateway.UserRepository
import domainInterface.usecase.user.ListUserUseCase

class ListUserUseCaseImpl[M[+_]] @Inject()(
  implicit monad: Monad[M],
  userRepository: UserRepository[M],
  taskRunner: DBIOTaskRunner[M]
) extends ListUserUseCase {

  override def call(arg: Unit)(implicit ec: ExecutionContext): Future[Seq[User]] = {
    taskRunner.run(for {
      users <- userRepository.resolveAll
    } yield users)
  }
}
