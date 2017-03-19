package adapter.output.presenter.user

import adapter.output.json.model.user.UserResponseConverter

import scala.concurrent.{ ExecutionContext, Future }

import play.api.mvc.{ Result, Results }

import domain.model.user.User
import domainInterface.adapter.output.UpdateUserResultPresenter

class UpdateUserResultPresenterImpl
  extends UpdateUserResultPresenter[Result]
    with UserResponseConverter with Results {

  override def response(arg: Future[Option[User]])(implicit ec: ExecutionContext): Future[Result] = {
    arg.map {
      case Some(user) => Ok(convertToJson(user))
      case _ => BadRequest("user not found")
    }
  }

}
