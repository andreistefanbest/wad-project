
package wad.user.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Andrei Stefan
 * @since July 24, 2019
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @NotNull
    private Integer userId;
    
    @Column(name = "full_name")
    @NotNull
    private String fullName;
    
    @Column(name = "mail")
    @Email
    private String mail;

    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Address address;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "password")
    @Length(min=6, max=32)
    private String password;
    
    @Column(name = "user_type")
    @NotNull
    private String userType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(userId, user.userId);

    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }
}
