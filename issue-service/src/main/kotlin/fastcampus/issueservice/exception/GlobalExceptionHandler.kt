package fastcampus.issueservice.exception

import com.auth0.jwt.exceptions.TokenExpiredException
import mu.KotlinLogging
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// 만든 이슈서비스 어플리케이션의 모든 에러를 다루는 핸들러
// controller + responsebody annotation
@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(ServerException::class)
    fun handleServerException(ex: ServerException): ErrorResponse {
        logger.error { ex.message } //kotlin logging을 이용해 서버 에러 메세지를 로그에 출력

        return ErrorResponse(code = ex.code, message = ex.message)
    }

    //TokenExpiredException Handler로 분리
    @ExceptionHandler(TokenExpiredException::class)
    fun handleTokenExpiredException(ex: TokenExpiredException): ErrorResponse {
        logger.error { ex.message } //kotlin logging을 이용해 서버 에러 메세지를 로그에 출력

        //정해진 에러를 리턴
        return ErrorResponse(code = 401, message = "Token Expired Error")
    }

    //예기치 못한 에러 handler
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ErrorResponse {
        logger.error { ex.message } //kotlin logging을 이용해 서버 에러 메세지를 로그에 출력

        //정해진 에러를 리턴, msg response에는 정해진 메세지로만 전달해야 해커에게 취약점을 들키지 않을 수 있음
        return ErrorResponse(code = 500, message = "Internal Server Error")
    }

}
