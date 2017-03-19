package adapter.modules

import com.google.inject.{ AbstractModule, TypeLiteral }

import adapter.output.presenter.user._

import play.api.mvc.Result

import domainInterface.adapter.output._

class PresenterModule extends AbstractModule {

  def configure(): Unit = {
    bind(new TypeLiteral[CreateUserResultPresenter[Result]]() {}).to(classOf[CreateUserResultPresenterImpl])
    bind(new TypeLiteral[UpdateUserResultPresenter[Result]]() {}).to(classOf[UpdateUserResultPresenterImpl])
    bind(new TypeLiteral[DeleteUserResultPresenter[Result]]() {}).to(classOf[DeleteUserResultPresenterImpl])
    bind(new TypeLiteral[ListUserResultPresenter[Result]]() {}).to(classOf[ListUserResultPresenterImpl])
  }
}