package wad.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.phone.repository.PhonesRepository;
import wad.purchase.dto.LastPurchaseDTO;
import wad.purchase.dto.PurchaseHistoryDTO;
import wad.purchase.entity.Purchase;
import wad.purchase.repository.PurchaseRepository;
import wad.user.entity.Address;
import wad.user.entity.User;
import wad.user.repositories.AddressRepository;
import wad.user.repositories.user.UserRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class PurchaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PhonesRepository phonesRepository;

    public LastPurchaseDTO getLastPurchaseDTO(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Optional<Address> userAddress = addressRepository.findById(user.getAddressId());

        if (userAddress.isEmpty()) {
            return new LastPurchaseDTO(user.getFullName(), user.getPhone());
        }

        Address address = userAddress.get();

        LastPurchaseDTO lastPurchaseDTO = new LastPurchaseDTO();
        lastPurchaseDTO.setFullName(user.getFullName());
        lastPurchaseDTO.setPhone(user.getPhone());
        lastPurchaseDTO.setBuilding(address.getBuilding());
        lastPurchaseDTO.setCity(address.getCity());
        lastPurchaseDTO.setCountry(address.getCountry());
        lastPurchaseDTO.setCounty(address.getCounty());
        lastPurchaseDTO.setStreet(address.getStreet());

        return lastPurchaseDTO;
    }

    public List<PurchaseHistoryDTO> getPurchasesHistory(String userId) {
        return purchaseRepository.findByUserId(userId)
                .stream()
                .map(this::purchaseHistoryDTOFromPurchase)
                .collect(toList());
    }

    private PurchaseHistoryDTO purchaseHistoryDTOFromPurchase(Purchase purchase) {
        String purchasedPhoneName = phonesRepository.findById(purchase.getPhoneId()).orElseThrow().getName();


        return new PurchaseHistoryDTO(purchasedPhoneName, purchase.getReceiverName(),
                                      purchase.getReceiverPhone(), purchase.getPurchaseDate());
    }
}
