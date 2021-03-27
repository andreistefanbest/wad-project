package wad.purchase.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class LastPurchaseDTO {

    @NonNull
    private String fullName;

    @NonNull
    private String phone;

    private String country;

    private String county;

    private String city;

    private String street;

    private String building;


}
