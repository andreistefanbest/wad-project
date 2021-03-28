package wad.user.repositories.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import wad.user.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Lazy
    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateUserPurchaseDetails(String userId, String addressId, String receiverPhone) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setAddressId(addressId);
        user.setPhone(receiverPhone);
        userRepository.save(user);
    }
}
