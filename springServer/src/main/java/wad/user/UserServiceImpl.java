
package wad.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import wad.phone.entities.PhonesRepository;
import wad.user.entities.*;
import wad.utils.GenericService;
import wad.utils.StringHasher;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Andrei Stefan
 * @since Mar 24, 2019
 */
@Component()
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
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StringHasher stringHasher;

    @Autowired
    private GenericService genericService;

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
    public User getUser(Integer id) throws Exception {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional()
    public Purchase buyPhone(Purchase purchase) throws Exception {
        purchase.setAddressId(addressRepository.save(purchase.getAddress()).getId());
        updateUserPurchaseDetails(purchase);

        return purchaseRepository.save(purchase);
    }

    private int updateUserPurchaseDetails(Purchase purchase) {
        return jdbcTemplate.update("UPDATE USER SET ADDRESS_ID = "
                + purchase.getAddressId() + ", PHONE = " + purchase.getReceiverPhone()
                + " WHERE USER_ID = " + purchase.getUserId());
    }

    @Override
    public List<Purchase> getPurchases(Integer userId) throws Exception {
        return genericService.fetchEntities(purchaseRepository)
                .stream()
                .filter(p -> p.getUserId().equals(userId))
                .map(this::withPhone)
                .collect(Collectors.toList());
    }

    private Purchase withPhone(Purchase p) {
        p.setPhone(phonesRepository.findById(p.getPhoneId()).get());
        return p;
    }
}
