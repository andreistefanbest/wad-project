
package wad.phone.entities;

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
public class Phones {
    @Id
    private Integer id;

    private String name;

    private String imageLink;

    private Date releaseDate;

    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PhoneTypes typeId;

    @JoinColumn(name = "specs_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Specs specsId;

    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Brands brandId;

    private Double price;

}
