
package wad.phone.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Andrei Stefan
 * @since Apr 8, 2019
 */
@Entity
@Table(name = "phones")
public class Phones implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_link")
    private String imageLink;

    @Column(name = "release_date")
    @Temporal(TemporalType.TIMESTAMP)
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

    @Column(name = "price")
    private Double price;

    public Phones() {
    }

    public Phones(Integer id) {
        this.id = id;
    }

    public Phones(Integer id, String name, Double price, String imageLink, Date releaseDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageLink = imageLink;
        this.releaseDate = releaseDate;
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


    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public PhoneTypes getTypeId() {
        return typeId;
    }

    public void setTypeId(PhoneTypes typeId) {
        this.typeId = typeId;
    }

    public Specs getSpecsId() {
        return specsId;
    }

    public void setSpecsId(Specs specsId) {
        this.specsId = specsId;
    }

    public Brands getBrandId() {
        return brandId;
    }

    public void setBrandId(Brands brandId) {
        this.brandId = brandId;
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
        if (!(object instanceof Phones)) {
            return false;
        }
        Phones other = (Phones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wad.phone.entities.Phones[ id=" + id + " ]";
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
