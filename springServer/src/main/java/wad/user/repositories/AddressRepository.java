package wad.user.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import wad.user.entity.Address;

public interface AddressRepository extends MongoRepository<Address, String> {
}
