package wad.review.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ReviewDTO {

    private String id;
    private String phoneId;
    private String userId;
    private String content;
    private Date date;

    private String userName;
}
