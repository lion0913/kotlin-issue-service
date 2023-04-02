package fastcampus.issueservice.domain.enums

enum class IssuePriority {
    LOW, MEDIUM, HIGH;

    companion object {
        //operator를 이용해 함수명을 사용하지 않고 생성자처럼 초기화할 수 있음
        operator fun invoke(priority: String) = valueOf(priority.uppercase())
    }
}
