package kr.co.knowledgerally.core.core.message;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorMessage {
    // Authorization
    public static String UNAUTHORIZED = "접근 권한이 없습니다.";

    // User
    public static String NOT_EXIST_USER = "존재하지 않는 사용자입니다.";

    // Coach
    public static String NOT_EXIST_COACH = "존재하지 않는 코치입니다.";
    public static String USER_NOT_COACH = "사용자가 코치가 아닙니다.";
}
