package wad.user.repositories.user;

public interface UserCustomRepository {

    void updateUserPurchaseDetails(Integer userId, Integer addressId, String receiverPhone);

}
