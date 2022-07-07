package kr.co.knowledgerally.api.lecture.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "수강 클래스 조회 모델", description = "수강 클래스를 나타내는 모델")
public class UserLectureDto {
    @ApiModelProperty(
            value = "신청서 읽기 모델",
            position = PropertyDisplayOrder.FORM,
            accessMode = ApiModelProperty.AccessMode.READ_ONLY
    )
    @JsonProperty(index = PropertyDisplayOrder.FORM)
    private FormDto.ReadOnly form;

    @ApiModelProperty(
            value = "클래스-info 읽기 모델",
            position = PropertyDisplayOrder.LECTURE_INFORMATION,
            accessMode = ApiModelProperty.AccessMode.READ_ONLY
    )
    @JsonProperty(index = PropertyDisplayOrder.LECTURE_INFORMATION)
    private LectureInformationDto.ReadOnly lectureInformation;

    private static class PropertyDisplayOrder {
        private static final int FORM = 0;
        private static final int LECTURE_INFORMATION = 1;
    }
}
