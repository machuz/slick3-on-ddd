package adapter.output.json.model.user

import play.api.libs.json.JsValue

import domain.model.user.User

trait UserResponseConverter {

  def convertToJson(entity: User): JsValue = {
    UserResponse(
      UserJsonModel(
        id = entity.id.value,
        name = entity.name)
    ).toJson
  }

  def convertToJson(entities: Seq[User]): JsValue = {
    UserSeqResponse(
      entities.map { entity =>
        UserJsonModel(
          id = entity.id.value,
          name = entity.name)
      }).toJson
  }

  def convertToJson(bool: Boolean): JsValue = {
    UserDeleteResponse(bool).toJson
  }

}
