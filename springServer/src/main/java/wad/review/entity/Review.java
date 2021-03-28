package wad.review.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    private String id;

    private String phoneId;

    private String userId;

    private String content;

    private Date date;
}
