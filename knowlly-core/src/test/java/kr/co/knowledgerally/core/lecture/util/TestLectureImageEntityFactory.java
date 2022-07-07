package kr.co.knowledgerally.core.lecture.util;

import kr.co.knowledgerally.core.core.util.TestEntityFactory;
import kr.co.knowledgerally.core.lecture.entity.LectureImage;
import kr.co.knowledgerally.core.lecture.entity.LectureInformation;

/**
 * 테스트용 강의 이미지 엔티티 생성 팩토리
 */
public class TestLectureImageEntityFactory implements TestEntityFactory<LectureImage> {
    private final TestEntityFactory<LectureInformation> testLectureInformationEntityFactory = new TestLectureInformationEntityFactory();

    /**
     * 테스트용 강의 이미지 엔티티를 생성한다.
     * lecture_info_id = null
     *
     * @param entityId 생성될 엔티티 Id
     * @return 생성된 강의 이미지 엔티티
     */
    @Override
    public LectureImage createEntity(long entityId) {
        return LectureImage.builder()
                .id(entityId)
                .lectureImgUrl(String.format("http://lecture%d.img.url", entityId))
                .build();
    }

    /**
     * 테스트용 클래스 이미지 엔티티를 생성한다.
     *
     * @param entityId      생성될 엔티티 Id
     * @param lectureInfoId 생성될 엔티티의 클래스-info Id
     * @return 생성된 클래스 이미지 엔티티
     */
    public LectureImage createEntity(long entityId, long lectureInfoId) {
        return LectureImage.builder()
                .id(entityId)
                .lectureInformation(testLectureInformationEntityFactory.createEntity(lectureInfoId))
                .lectureImgUrl(String.format("http://lecture%d.img.url", entityId))
                .build();
    }
}
