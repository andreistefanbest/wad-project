
package wad.phone.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Andrei Stefan
 * @since Apr 8, 2019
 */
@Entity
@Table(name = "phone_types")
public class PhoneTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public PhoneTypes() {
    }

    public PhoneTypes(Integer id) {
        this.id = id;
    }

    public PhoneTypes(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhoneTypes)) {
            return false;
        }
        PhoneTypes other = (PhoneTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wad.phone.entities.PhoneTypes[ id=" + id + " ]";
    }

}
