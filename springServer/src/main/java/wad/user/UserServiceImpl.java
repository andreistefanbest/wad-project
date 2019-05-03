
package wad.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import wad.phone.entities.PhonesRepository;
import wad.user.entities.*;
import wad.utils.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
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

    @Override
    public User signIn(String name, String mail, String password) throws Exception {
        var user = new User();
        user.setFullName(name);
        user.setMail(mail);
        user.setPassword(StringUtils.getHash(password));
        user.setUserType("basic");
        
        return userRepository.save(user);
    }

    @Override
    public User logIn(String mail, String password) throws Exception {
        for (var u : userRepository.findAll()) {
            if (!u.getMail().equals(mail)) {
                continue;
            }
            
            if (u.getPassword().equals(StringUtils.getHash(password))) {
                return u;
            }

            break;
        }

        return null;
    }

    @Override
    public User getUser(Integer id) throws Exception {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional()
    public Purchase buyPhone(Purchase purchase) throws Exception {
        var address = addressRepository.save(purchase.getAddress());

        jdbcTemplate.update("UPDATE USER SET ADDRESS_ID = "
                + address.getId() + ", PHONE = " + purchase.getReceiverPhone()
                + " WHERE USER_ID = " + purchase.getUserId());

        purchase.setAddressId(address.getId());
        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> getPurchases(Integer userId) throws Exception {
        return StreamSupport.stream(purchaseRepository.findAll().spliterator(), false)
                .filter(p -> p.getUserId().equals(userId))
                .map(this::withPhone)
                .collect(Collectors.toList());
    }

    private Purchase withPhone(Purchase p) {
        p.setPhone(phonesRepository.findById(p.getPhoneId()).get());
        return p;
    }
}
