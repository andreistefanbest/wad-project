package wad.user.repositories.user;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final UserRepository userRepository;

    @Lazy
    public UserRepositoryImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void updateUserPurchaseDetails(String userId, String addressId, String receiverPhone) {
        userRepository.findById(userId).subscribe(user -> {
            user.setAddressId(addressId);
            user.setPhone(receiverPhone);
            userRepository.save(user);
        });
    }
}
