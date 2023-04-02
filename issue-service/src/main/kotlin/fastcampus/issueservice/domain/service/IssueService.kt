package fastcampus.issueservice.domain.service

import fastcampus.issueservice.domain.Issue
import fastcampus.issueservice.domain.IssueRepository
import fastcampus.issueservice.domain.model.IssueRequest
import fastcampus.issueservice.domain.model.IssueResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class IssueService(
    //생성자 주입
    private val issueRepository: IssueRepository,
) {
    @Transactional
    fun create(userId: Long, request: IssueRequest): IssueResponse {
        //issue가 Issue 객체에 의해 생성
        val issue = Issue(
            summary = request.summary,
            description = request.description,
            userId = userId, //토큰 기반의 유저아이디
            type = request.type,
            priority = request.priority,
            status = request.status,
        )
        // companion을 이용해 of를 사용하지 않고 바로 생성자처럼 사용 가능
        return IssueResponse(issueRepository.save(issue))
    }
}
