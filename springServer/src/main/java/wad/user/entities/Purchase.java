
package wad.user.entities;

import org.hibernate.annotations.Formula;
import wad.phone.entities.Phones;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Andrei Stefan
 * @since Apr 11, 2019
 */
@Entity
@Table(name = "purchase")
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_phone")
    private String receiverPhone;

    @Column(name = "purchase_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;

    @Column(name = "phone_id")
    private Integer phoneId;

    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "user_id")
    private Integer userId;

    @JoinColumn(name = "phone_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Transient
    private Phones phone;

    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Transient
    private Address address;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    @Transient
    private User user;

    @Formula("(select p.price from phones p where p.id = phone_id)")
    private Double price;

    public Purchase() {
    }

    public Purchase(Integer id) {
        this.id = id;
    }

    public Purchase(Integer id, String receiverName, String receiverPhone, Date purchaseDate) {
        this.id = id;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.purchaseDate = purchaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Phones getPhone() {
        return phone;
    }

    public void setPhone(Phones phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        if (!(object instanceof Purchase)) {
            return false;
        }
        Purchase other = (Purchase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wad.user.entities.Purchase[ id=" + id + " ]";
    }

}
