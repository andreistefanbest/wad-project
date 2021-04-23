package wad.user.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import wad.user.entity.Address;

public interface AddressRepository extends ReactiveMongoRepository<Address, String> {
}
