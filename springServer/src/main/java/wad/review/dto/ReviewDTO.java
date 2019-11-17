package wad.review.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ReviewDTO {

    private Integer id;
    private Integer phoneId;
    private Integer userId;
    private String content;
    private Date date;

    private String userName;
}
