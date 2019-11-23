package wad.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CourseDTO {
    String name;
    String year;
    Boolean isInLastThreeYears;
}
