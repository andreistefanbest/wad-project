
package wad.user.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author Andrei Stefan
 * @since July 24, 2019
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @NotNull
    private Integer userId;

    @NotNull
    private String fullName;

    @Email
    private String mail;

    //    @JoinColumn(name = "address_id", referencedColumnName = "id")
//    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Address address;


    private Integer addressId;

    private String phone;

    @Length(min = 6, max = 32)
    private String password;

    @NotNull
    private String userType;
}
