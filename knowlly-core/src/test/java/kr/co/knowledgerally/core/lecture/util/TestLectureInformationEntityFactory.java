package kr.co.knowledgerally.core.lecture.util;

import kr.co.knowledgerally.core.core.util.TestEntityFactory;
import kr.co.knowledgerally.core.lecture.entity.Category;
import kr.co.knowledgerally.core.lecture.entity.LectureImage;
import kr.co.knowledgerally.core.lecture.entity.LectureInformation;
import kr.co.knowledgerally.core.coach.entity.Coach;
import kr.co.knowledgerally.core.coach.util.TestCoachEntityFactory;

import java.util.*;

/**
 * 테스트용 강의정보 엔티티 생성 팩토리
 */
public class TestLectureInformationEntityFactory implements TestEntityFactory<LectureInformation> {
    private final TestCoachEntityFactory testCoachEntityFactory = new TestCoachEntityFactory();
    private final TestEntityFactory<Category> testCategoryEntityFactory = new TestCategoryEntityFactory();

    /**
     * 테스트용 강의정보 엔티티를 생성한다.
     *
     * @param entityId 생성될 엔티티 Id
     * @return 생성된 강의정보 엔티티
     */
    @Override
    public LectureInformation createEntity(long entityId) {
        return createEntity(entityId, 2L, 5L,3L, 2);
    }

    /**
     * 테스트용 강의정보 엔티티를 생성한다.
     *
     * @param entityId   생성될 엔티티 Id
     * @param coachId    생성될 엔티티의 코치 Id
     * @param categoryId 생성될 엔티티 카테고리 Id
     * @return 생성된 강의정보 엔티티
     */
    public LectureInformation createEntity(long entityId, long coachId, long categoryId, long userId, long lectureImageNum) {
        Set<LectureImage> lectureImageSet = new LinkedHashSet<>();
        for (long index=1; index<=lectureImageNum; index++) {
            lectureImageSet.add(
                    LectureImage.builder()
                            .id(index)
                            .lectureInformation(LectureInformation.builder().build())
                            .lectureImgUrl(String.format("http://lecture%d.img%d.url", entityId, index))
                            .build()
            );
        }

        return LectureInformation.builder()
                .id(entityId)
                .coach(testCoachEntityFactory.createEntity(coachId, userId))
                .category(testCategoryEntityFactory.createEntity(categoryId))
                .lectureImageSet(lectureImageSet)
                .topic(String.format("테스트%d 제목", entityId))
                .introduce(String.format("안녕하세요. 테스트%d 입니다.", entityId))
                .price(1)
                .build();
    }
}
