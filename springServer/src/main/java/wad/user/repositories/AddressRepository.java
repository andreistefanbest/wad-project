package wad.user.repositories;

import org.springframework.data.repository.CrudRepository;
import wad.user.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
