
package wad.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wad.user.entities.*;
import wad.utils.StringUtils;

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
    public int logIn(String mail, String password) throws Exception {
        var success = false;
        for (var u : userRepository.findAll()) {
            if (!u.getMail().equals(mail)) {
                continue;
            }
            
            success = u.getPassword().equals(StringUtils.getHash(password));
            break;
        }
        
        return success ? 1 : 0;
    }

    @Override
    public Purchase buyPhone(Purchase purchase) throws Exception {
        purchase.setAddressId(addressRepository.save(purchase.getAddress()).getId());
        return purchaseRepository.save(purchase);
    }

}
