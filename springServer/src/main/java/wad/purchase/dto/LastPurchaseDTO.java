package wad.purchase.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
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
