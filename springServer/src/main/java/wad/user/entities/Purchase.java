
package wad.user.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import wad.phone.entities.Phones;

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
    private Integer id;

    private String receiverName;

    private String receiverPhone;

    private Date purchaseDate;

    private Integer phoneId;

    private Integer addressId;

    private Integer userId;

//    @JoinColumn(name = "phone_id", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    @Transient
private Phones phone;

    //    @JoinColumn(name = "address_id", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    @Transient
    private Address address;

    //    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//    @ManyToOne(optional = false)
//    @Transient
    private User user;

    //    @Formula("(select p.price from phones p where p.id = phone_id)")
    private Double price;
}
