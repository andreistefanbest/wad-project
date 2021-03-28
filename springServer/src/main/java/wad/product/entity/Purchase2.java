
package wad.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Andrei Stefan
 * @since Apr 15, 2019
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase2 {

    @Id
    private String id;

    private int userId;

    private Date date;

    private int numOfItems;

    private int totalPrice;
}
