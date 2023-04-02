package fastcampus.issueservice.model

import fastcampus.issueservice.domain.Comment

data class CommentRequest (
    val body: String,

)

data class CommentResponse (
    val id: Long,
    val issueId: Long,
    val userId: Long,
    val body: String,
    val username: String? = null,
)

//확장함수를 통한 dto변환(2)
fun Comment.toResponse() = CommentResponse(
    id = id!!,
    issueId = issue.id!!,
    userId = userId,
    body = body,
    username = username,
)
