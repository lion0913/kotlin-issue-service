package fastcampus.issueservice.domain.web

import fastcampus.issueservice.config.AuthUser
import fastcampus.issueservice.domain.model.IssueRequest
import fastcampus.issueservice.domain.service.IssueService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues")
class IssueController(
    private val issueService: IssueService,
) {
    @PostMapping
    fun create(
        authUser: AuthUser, //HandlerMethodArgumentResolver는 controller의 method인자로 AuthUser가 있는 경우 resolver를 통해 더미유저를 만든다.
        @RequestBody request: IssueRequest,
    ) = issueService.create(authUser.userId, request)
}
