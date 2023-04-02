package fastcampus.issueservice.domain.enums

enum class IssueType {
    //type 정의
    BUG, TASK;

    // 외부에서 사용할 수 있게 하기 위함
    companion object {
        //정적 팩토리 함수 생성 -> java style
//        fun of(type: String) = valueOf(type.uppercase())
//        val type = IssueType.of("BUG")

        //kotlin style
        operator fun invoke(type: String) = valueOf(type.uppercase())
        //operator를 이용하면 함수명 없이(생성자를 사용한 것처럼) 객체를 초기화 할 수 있다.
        // val type = IssueType("BUG")
    }
}
