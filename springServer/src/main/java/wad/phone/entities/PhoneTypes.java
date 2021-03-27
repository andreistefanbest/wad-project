
package wad.phone.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Andrei Stefan
 * @since Apr 8, 2019
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneTypes {

    @Id
    private Integer id;

    private String name;

}