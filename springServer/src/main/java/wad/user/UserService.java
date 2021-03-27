package wad.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.purchase.dto.NewPurchaseDTO;
import wad.purchase.entity.Purchase;
import wad.purchase.repository.PurchaseRepository;
import wad.user.entity.User;
import wad.user.repositories.AddressRepository;
import wad.user.repositories.user.UserRepository;
import wad.utils.StringHasher;

import java.util.Objects;

/**
 * @author Andrei Stefan
 * @since Mar 24, 2019
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private StringHasher stringHasher;

    public User signIn(String name, String mail, String password) throws Exception {
        var user = new User();
        user.setFullName(name);
        user.setMail(mail);
        user.setPassword(stringHasher.getHash(password));
        user.setUserType("basic");

        return userRepository.save(user);
    }

    public User logIn(String mail, String password) throws Exception {
        for (var user : userRepository.findAll()) {
            if (matchMailAndPassword(mail, password, user)) {
                return user;
            }
        }

        return null;
    }

    private boolean matchMailAndPassword(String mail, String password, User user) throws Exception {
        return Objects.equals(user.getMail(), mail) &&
                Objects.equals(user.getPassword(), stringHasher.getHash(password));
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public Integer buyPhone(NewPurchaseDTO newPurchaseDTO) {
        Purchase purchase = newPurchaseDTO.getPurchase();

        Integer newAddressId = addressRepository.save(newPurchaseDTO.getAddress()).getId();
        purchase.setAddressId(newAddressId);

        updateUserPurchaseDetails(purchase);

        return purchaseRepository.save(purchase).getId();
    }

    private void updateUserPurchaseDetails(Purchase purchase) {
        userRepository.updateUserPurchaseDetails(purchase.getUserId(), purchase.getAddressId(), purchase.getReceiverPhone());
    }
}
