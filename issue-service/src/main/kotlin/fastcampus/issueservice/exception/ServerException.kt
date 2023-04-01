package fastcampus.issueservice.exception

//최상위 exception 정의
sealed class ServerException(
    val code: Int,
    override val message: String,
) : RuntimeException(message) //runtimeException을 상속

data class NotFoundException(
    override val message: String,
) : ServerException(404, message) //custom code에서의 404

data class UnAuthorizedException(
    override val message: String = "인증 정보가 잘못되었습니다.",
) : ServerException(401, message)
