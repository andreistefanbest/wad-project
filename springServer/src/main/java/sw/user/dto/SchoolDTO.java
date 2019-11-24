package sw.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class SchoolDTO {
    String name;
    String year;
    List<SubjectDTO> subjects;
}
