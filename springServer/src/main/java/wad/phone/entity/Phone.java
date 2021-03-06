
package wad.phone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Andrei Stefan
 * @since Apr 8, 2019
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id
    private String id;

    private String name;

    private String imageLink;

    private Date releaseDate;

    private String typeId;

    private String specsId;

    private String brandId;

    private Double price;

}
