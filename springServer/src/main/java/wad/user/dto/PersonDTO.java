package wad.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PersonDTO {
    String name;
    String gender;
    String website;
    String address;
    String telephone;
    String email;
    List<SchoolDTO> schools;
    List<CourseDTO> extraCourses;
    List<AwardDTO> awards;
    List<MembershipDTO> memberships;
    List<JobDTO> jobs;
    List<String> interests;
}
