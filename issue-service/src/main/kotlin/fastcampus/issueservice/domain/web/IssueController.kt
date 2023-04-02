package fastcampus.issueservice.domain.web

import fastcampus.issueservice.config.AuthUser
import fastcampus.issueservice.domain.enums.IssueStatus
import fastcampus.issueservice.domain.model.IssueRequest
import fastcampus.issueservice.domain.service.IssueService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

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

    @GetMapping
    fun getAll(
        authUser: AuthUser,
        @RequestParam(required = false, defaultValue = "TODO") status: IssueStatus,
    ) = issueService.getAll(status)

    @GetMapping("/{id}")
    fun get(
        authUser: AuthUser,
        @PathVariable id: Long,
    ) = issueService.get(id)

    @PutMapping("/{id}")
    fun edit(
        authUser: AuthUser,
        @PathVariable id: Long,
        @RequestBody request: IssueRequest,
    ) = issueService.edit(authUser.userId, id, request)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //리소스 삭제 성공 시 204 NOcontent 상태 코드로 응답
    fun delete(
        authUser: AuthUser,
        @PathVariable id: Long,
    ) {
        issueService.delete(id)
    }
}
