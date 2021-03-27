
package wad.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Andrei Stefan
 * @since Apr 1, 2019
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private Integer productId;

    private String name;

    private int unitPrice;

    private String type;
    
    private String description;
}
