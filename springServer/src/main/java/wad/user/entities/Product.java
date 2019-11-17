
package wad.user.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Andrei Stefan
 * @since Apr 1, 2019
 */
@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "unit_price")
    private int unitPrice;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "description")
    private String description;

}
