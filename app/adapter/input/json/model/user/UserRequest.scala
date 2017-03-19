package adapter.input.json.model.user

import scalaz.\/
import scalaz.Scalaz._

import play.api.Logger
import play.api.data.validation.ValidationError
import play.api.libs.json.{ Format, JsPath, JsValue, Json }

case class UserCreateRequest(
  name: String
)

object UserCreateRequest {

  implicit val format: Format[UserCreateRequest] = Json.format[UserCreateRequest]
  def fromJson(json: JsValue): \/[Seq[(JsPath, Seq[ValidationError])], UserCreateRequest] = Json.fromJson(json)(format).fold(
    valid = s => s.right,
    invalid = e => {
      e.foreach { case (path, errors) =>
        Logger.error(s"""path:[${path.toString}] msg:[${errors.map(x => x.message).mkString(",")}] arg:[${errors.map(_.args.toString).mkString(",")}] """)
      }
      e.left
    }
  )
}

case class UserUpdateRequest(
  userId: String,
  name: String
)

object UserUpdateRequest {
  implicit val format: Format[UserUpdateRequest] = Json.format[UserUpdateRequest]
  def fromJson(json: JsValue): \/[Seq[(JsPath, Seq[ValidationError])], UserUpdateRequest] = Json.fromJson(json)(format).fold(
    valid = s => s.right,
    invalid = e => {
      e.foreach { case (path, errors) =>
        Logger.error(s"""path:[${path.toString}] msg:[${errors.map(x => x.message).mkString(",")}] arg:[${errors.map(_.args.toString).mkString(",")}] """)
      }
      e.left
    }
  )
}

case class UserDeleteRequest(
  userId: String
) {
  def toJson: JsValue = Json.toJson(this)(UserDeleteRequest.format)
}

object UserDeleteRequest {
  implicit val format: Format[UserDeleteRequest] = Json.format[UserDeleteRequest]
  def fromJson(json: JsValue): \/[Seq[(JsPath, Seq[ValidationError])], UserDeleteRequest] = Json.fromJson(json)(format).fold(
    valid = s => s.right,
    invalid = e => {
      e.foreach { case (path, errors) =>
        Logger.error(s"""path:[${path.toString}] msg:[${errors.map(x => x.message).mkString(",")}] arg:[${errors.map(_.args.toString).mkString(",")}] """)
      }
      e.left
    }
  )
}
