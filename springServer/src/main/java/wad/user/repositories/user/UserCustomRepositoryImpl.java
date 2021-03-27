package wad.user.repositories.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wad.user.entity.User;

@Component
public class UserCustomRepositoryImpl implements UserCustomRepository {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateUserPurchaseDetails(Integer userId, Integer addressId, String receiverPhone) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setAddressId(addressId);
        user.setPhone(receiverPhone);
        userRepository.save(user);
    }
}
