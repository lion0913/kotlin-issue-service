package fastcampus.issueservice.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
class WebConfig(
    private val authUserHandlerArgumentResolver: AuthUserHandlerArgumentResolver
) : WebMvcConfigurationSupport() {
    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.apply {
            add(authUserHandlerArgumentResolver)
            // 스프링에서 내부적으로 등록한 리스트를 반복을 돌면서 supportsParameter의 조건에 부합이 될 경우 하위에 있는 resolveArgument를 통해서 controller의 인자에 들어가는 AuthUser에 대해서 자동으로 값을 넣어주게 된다.
        }
    }


}

//컨트롤러에 들어오는 특정 어노테이션에 대해서 해당 조건이 맞는다고 할 경우, resolveArgument 통해서 해당 어노테이션, 객체에 대해 값을 세팅할 수 잇게 해주는 유용한 인터페이스이다.
//jwt인증과정을 간소화하고자 함(spring security 사용x)
@Component
class AuthUserHandlerArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean =
        AuthUser::class.java.isAssignableFrom(parameter.parameterType)//파라미터로 들어온 파라미터 타입과 authuser type이 동일하다면 resolveArgument함수가 동작이 됨.


    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        return AuthUser( // 현재는 더미유저 사용
            userId = 1,
            username = "테스트",

        )
    }
}

data class AuthUser(
    val userId: Long,
    val username: String,
    val profileUrl: String? = null,
)
