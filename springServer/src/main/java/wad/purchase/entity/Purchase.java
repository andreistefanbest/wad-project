
package wad.purchase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Andrei Stefan
 * @since Apr 11, 2019
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    @Id
    private String id;

    private String receiverName;

    private String receiverPhone;

    private Date purchaseDate;

    private String phoneId;

    private String addressId;

    private String userId;
}
