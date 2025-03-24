package hs.kr.casper.configserver.infrastructure.error.message

object ErrorMessages {
    const val ENTRY_NOT_FOUND = "요청한 리소스를 찾을 수 없습니다"
    const val INTERNAL_SERVER_ERROR = "서버 내부 오류"

    const val INVALID_REQUEST = "잘못된 요청입니다"
    const val INVALID_JSON = "잘못된 JSON 형식입니다"
    const val INVALID_PARAMETER = "잘못된 파라미터입니다"
    const val INVALID_METHOD_ARGUMENT = "잘못된 메소드 인자입니다"

    const val UNAUTHORIZED = "인증되지 않은 사용자입니다"
    const val FORBIDDEN = "권한이 없습니다"
    const val CONFLICT = "이미 존재하는 리소스입니다"
}