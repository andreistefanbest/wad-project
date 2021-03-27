package wad.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseHistoryDTO {

    private String phoneName;

    private String receiverName;

    private String receiverPhone;

    private Date purchaseDate;

}
