package fastcampus.issueservice.domain

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

//추상 클래스로 선언 -> 공통 속성을 정의하는 부모 엔티티로 상용
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class) //CreatedDate, LastModifiedDate가 자동으로 조건에 따라서 들어가게됨
abstract class BaseEntity (
    // 생성, 수정일자를 자동으로 저장 -> 해당 어노테이션을 사용하기 위해선 EntityListeners 어노테이션을 사용해야함
    @CreatedDate
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null,
)
