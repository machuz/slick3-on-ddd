package adapter.ioGateway.repository.user

import adapter.ioGateway.rds.slick.dao.UserDataModel

import domain.model.user.{ User, UserId }

trait UserConverter {

  def convertToDomainModel(dataModel: UserDataModel): User = {
    User(
      id = UserId(dataModel.id),
      name = dataModel.name)
  }

  def convertToDataModel(domainModel: User): UserDataModel = {
    UserDataModel(
      id = domainModel.id.value,
      name = domainModel.name
    )
  }

}
