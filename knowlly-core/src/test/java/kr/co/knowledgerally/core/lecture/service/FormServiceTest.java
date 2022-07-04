package kr.co.knowledgerally.core.lecture.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import kr.co.knowledgerally.core.annotation.KnowllyDataTest;
import kr.co.knowledgerally.core.core.exception.ResourceNotFoundException;
import kr.co.knowledgerally.core.lecture.entity.Form;
import kr.co.knowledgerally.core.lecture.entity.Lecture;
import kr.co.knowledgerally.core.user.entity.User;
import kr.co.knowledgerally.core.user.util.TestUserEntityFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@KnowllyDataTest
@DatabaseSetup({
        "classpath:dbunit/entity/user.xml",
        "classpath:dbunit/entity/coach.xml",
        "classpath:dbunit/entity/category.xml",
        "classpath:dbunit/entity/lecture_information.xml",
        "classpath:dbunit/entity/lecture.xml",
        "classpath:dbunit/entity/form.xml",
})
@Import(FormService.class)
class FormServiceTest {
    @Autowired
    FormService formService;

    @Test
    void ID로_신청서_찾기_테스트() {
        Form form = formService.findById(1L);

        assertEquals(1L, form.getId());
        assertEquals(1L, form.getLecture().getId());
        assertEquals(4L, form.getUser().getId());
        assertEquals("신청서를 받아주세요!", form.getContent());
        assertEquals(Form.State.ACCEPT, form.getState());
        assertTrue(form.isActive());
        assertEquals(LocalDateTime.of(2022, 6, 13, 22, 48, 17), form.getCreatedAt());
        assertEquals(LocalDateTime.of(2022, 6, 13, 22, 48, 17), form.getUpdatedAt());
    }

    @Test
    void ID로_신청서_찾기_테스트_없으면_throw() {
        assertThrows(ResourceNotFoundException.class, () -> {
            formService.findById(9999L);
        });
    }

    @Test
    void 사용자로_신청서_찾기_테스트() {
        User testUser = new TestUserEntityFactory().createEntity(3L);

        List<Form> forms = formService.findAllByUser(testUser);

        assertEquals(2, forms.size());
        assertEquals(5L, forms.get(0).getId());
        assertEquals(3L, forms.get(0).getLecture().getId());
        assertEquals(3L, forms.get(0).getUser().getId());
        assertEquals("신청 거절해주세요", forms.get(0).getContent());
        assertEquals(Form.State.REJECT, forms.get(0).getState());
        assertTrue(forms.get(0).isActive());
        assertEquals(LocalDateTime.of(2022, 6, 13, 22, 51, 3), forms.get(0).getCreatedAt());
        assertEquals(LocalDateTime.of(2022, 6, 13, 22, 51, 4), forms.get(0).getUpdatedAt());
        assertEquals(2L, forms.get(1).getId());
        assertEquals(2L, forms.get(1).getLecture().getId());
        assertEquals(3L, forms.get(1).getUser().getId());
        assertEquals("제 신청서를 받아주세요!", forms.get(1).getContent());
        assertEquals(Form.State.ACCEPT, forms.get(1).getState());
        assertTrue(forms.get(1).isActive());
        assertEquals(LocalDateTime.of(2022, 6, 13, 22, 48, 18), forms.get(1).getCreatedAt());
        assertEquals(LocalDateTime.of(2022, 6, 13, 22, 48, 18), forms.get(1).getUpdatedAt());
    }

    @Test
    void 사용자로_신청서_목록_찾기_신청서_상태_조회_테스트() {
        User testUser = new TestUserEntityFactory().createEntity(3L);

        List<Form> forms = formService.findAllByUserAndState(testUser, Form.State.ACCEPT);

        assertEquals(1, forms.size());
        assertEquals(2L, forms.get(0).getId());
        assertEquals(2L, forms.get(0).getLecture().getId());
        assertEquals(3L, forms.get(0).getUser().getId());
        assertEquals("제 신청서를 받아주세요!", forms.get(0).getContent());
        assertEquals(Form.State.ACCEPT, forms.get(0).getState());
        assertTrue(forms.get(0).isActive());
        assertEquals(LocalDateTime.of(2022, 6, 13, 22, 48, 18), forms.get(0).getCreatedAt());
        assertEquals(LocalDateTime.of(2022, 6, 13, 22, 48, 18), forms.get(0).getUpdatedAt());
    }

    @Test
    void 사용자로_신청서_목록_찾기_클래스_상태_조회_테스트() {
        User testUser = new TestUserEntityFactory().createEntity(3L);

        List<Form> forms = formService.findAllByUserAndLectureState(testUser, Lecture.State.ON_GOING);

        assertEquals(1, forms.size());
        assertEquals(2L, forms.get(0).getId());
        assertEquals(2L, forms.get(0).getLecture().getId());
        assertEquals(3L, forms.get(0).getUser().getId());
        assertEquals("제 신청서를 받아주세요!", forms.get(0).getContent());
        assertEquals(Form.State.ACCEPT, forms.get(0).getState());
        assertTrue(forms.get(0).isActive());
        assertEquals(LocalDateTime.of(2022, 6, 13, 22, 48, 18), forms.get(0).getCreatedAt());
        assertEquals(LocalDateTime.of(2022, 6, 13, 22, 48, 18), forms.get(0).getUpdatedAt());
    }
}