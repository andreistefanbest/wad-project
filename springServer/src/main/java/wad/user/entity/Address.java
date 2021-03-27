
package wad.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Andrei Stefan
 * @since Apr 11, 2019
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    private Integer id;

    private String country;

    private String county;

    private String city;
    
    private String street;
    
    private String building;
}
