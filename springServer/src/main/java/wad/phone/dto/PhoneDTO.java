package wad.phone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import wad.phone.entity.Brand;
import wad.phone.entity.PhoneType;
import wad.phone.entity.Specs;

import java.util.Date;

@Data
@NoArgsConstructor
public class PhoneDTO {
    private Integer id;

    private String name;

    private String imageLink;

    private Date releaseDate;

    private PhoneType phoneType;

    private Specs specs;

    private Brand brand;

    private Double price;
}
