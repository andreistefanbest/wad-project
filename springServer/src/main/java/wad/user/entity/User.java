
package wad.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    @NonNull
    private String userId;

    @NonNull
    private String fullName;

    private String mail;

    private String addressId;

    private String phone;

    private String password;

    @NonNull
    private String userType;
}
