
package wad.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    private String userId;

    @NotNull
    private String fullName;

    @Email
    private String mail;

    private String addressId;

    private String phone;

    @Size(min = 6, max = 32)
    private String password;

    @NotNull
    private String userType;
}
