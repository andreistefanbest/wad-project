package wad.purchase.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import wad.purchase.entity.Purchase;
import wad.user.entity.Address;

@Data
@NoArgsConstructor
public class NewPurchaseDTO {

    private Purchase purchase;

    private Address address;

}
