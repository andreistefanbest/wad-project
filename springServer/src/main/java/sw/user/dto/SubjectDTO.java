package sw.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SubjectDTO {
    String name;
    String keyword;
    String year;
    String grade;
    Boolean isTech;
}
