package fastcampus.issueservice.domain.service

import fastcampus.issueservice.domain.Issue
import fastcampus.issueservice.domain.IssueRepository
import fastcampus.issueservice.domain.enums.IssueStatus
import fastcampus.issueservice.domain.model.IssueRequest
import fastcampus.issueservice.domain.model.IssueResponse
import fastcampus.issueservice.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    @Transactional(readOnly = true)
    fun getAll(status: IssueStatus) =
        issueRepository.findAllByStatusOrderByCreatedAtDesc(status)
            ?.map {IssueResponse(it)} //map을 이용해 issue가 IssuerResponse type으로 변환이 된다

    @Transactional(readOnly = true)
    fun get(id: Long): IssueResponse {
        val issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("이슈가 존재하지 않습니다")
        return IssueResponse(issue)
    }

    @Transactional
    fun edit(userId: Long, id: Long, request: IssueRequest): IssueResponse {
        //issue가 존재하는지 체크
        val issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("이슈가 존재하지 않습니다")

        return with(issue) {
            summary = request.summary
            description = request.description
            this.userId = userId
            type = request.type
            priority = request.priority
            status = request.status

            IssueResponse(issueRepository.save(this))
        }
    }

    fun delete(id: Long) {
        issueRepository.deleteById(id)
    }


}
