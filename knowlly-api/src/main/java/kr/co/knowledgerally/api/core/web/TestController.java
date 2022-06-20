package kr.co.knowledgerally.api.core.web;

import kr.co.knowledgerally.api.core.dto.ApiResult;
import kr.co.knowledgerally.core.core.exception.KnowllyException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 테스트용 컨트롤러
 */
@ApiIgnore
@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<ApiResult<TestObject>> test() {
        return ResponseEntity.ok(ApiResult.ok(new TestObject("test is ok!!!")));
    }

    @GetMapping("/authenticated-test")
    public ResponseEntity<ApiResult<TestObject>> authenticatedTest() {
        return ResponseEntity.ok(ApiResult.ok(new TestObject("test is ok!!!")));
    }

    @GetMapping("/test-failure")
    public ResponseEntity<ApiResult<TestObject>> testFailure() {
        throw new KnowllyException("net.mureng.mureng failure");
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class TestObject {
        private String result;
    }
}
