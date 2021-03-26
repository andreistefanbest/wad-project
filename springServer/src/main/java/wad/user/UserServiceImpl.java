package wad.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wad.phone.repositories.PhonesRepository;
import wad.user.entities.Purchase;
import wad.user.entities.User;
import wad.user.repositories.AddressRepository;
import wad.user.repositories.PurchaseRepository;
import wad.user.repositories.user.UserRepository;
import wad.utils.StringHasher;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Andrei Stefan
 * @since Mar 24, 2019
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PhonesRepository phonesRepository;

    @Autowired
    private StringHasher stringHasher;

    @Override
    public User signIn(String name, String mail, String password) throws Exception {
        var user = new User();
        user.setFullName(name);
        user.setMail(mail);
        user.setPassword(stringHasher.getHash(password));
        user.setUserType("basic");

        return userRepository.save(user);
    }

    @Override
    public User logIn(String mail, String password) throws Exception {
        for (var user : userRepository.findAll()) {
            if (matchMailAndPassword(mail, password, user)) {
                return user;
            }
        }

        return null;
    }

    private boolean matchMailAndPassword(String mail, String password, User user) throws Exception {
        return user.getMail().equals(mail) &&
                user.getPassword().equals(stringHasher.getHash(password));
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Purchase buyPhone(Purchase purchase) {
        purchase.setAddressId(addressRepository.save(purchase.getAddress()).getId());
        updateUserPurchaseDetails(purchase);

        return purchaseRepository.save(purchase);
    }

    private void updateUserPurchaseDetails(Purchase purchase) {
        userRepository.updateUserPurchaseDetails(purchase.getUserId(), purchase.getAddressId(), purchase.getReceiverPhone());
    }

    @Override
    public List<Purchase> getPurchases(Integer userId) {
        return purchaseRepository.findByUserId(userId)
                .stream()
                .map(this::withPhone)
                .collect(toList());
    }

    private Purchase withPhone(Purchase p) {
        p.setPhone(phonesRepository.findById(p.getPhoneId()).get());
        return p;
    }
}
