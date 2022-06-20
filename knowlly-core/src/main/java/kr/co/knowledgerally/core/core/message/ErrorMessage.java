package kr.co.knowledgerally.core.core.message;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorMessage {
    // Authorization
    public static String UNAUTHORIZED = "접근 권한이 없습니다.";

    // Member
    public static String NOT_EXIST_USER = "존재하지 않는 사용자입니다.";
}
