package adapter.output.json.model.user

import play.api.libs.json.{ Format, JsValue, Json }

case class UserJsonModel(
  id: String,
  name: String
)

object UserJsonModel {
  implicit val format: Format[UserJsonModel] = Json.format[UserJsonModel]
}

case class UserDeleteResponse(
  data: Boolean
) {
  def toJson: JsValue = Json.toJson(this)(UserDeleteResponse.format)
}

object UserDeleteResponse {
  implicit val format: Format[UserDeleteResponse] = Json.format[UserDeleteResponse]
}

case class UserSeqResponse(
  data: Seq[UserJsonModel]
) {
  def toJson: JsValue = Json.toJson(this)(UserSeqResponse.format)
}

object UserSeqResponse {
  implicit val format: Format[UserSeqResponse] = Json.format[UserSeqResponse]
}

case class UserResponse(
  data: UserJsonModel
) {
  def toJson: JsValue = Json.toJson(this)(UserResponse.format)
}

object UserResponse {
  implicit val format: Format[UserResponse] = Json.format[UserResponse]
}