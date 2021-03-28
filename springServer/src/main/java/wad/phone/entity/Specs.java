
package wad.phone.entity;

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
public class Specs {

    @Id
    private String id;

    private Integer ramCapacity;

    private String ramType;

    private String cpu;

    private Double clockSpeed;

    private Boolean bluetooth;

    private String networkSupport;

    private Integer storageCapacity;
}
