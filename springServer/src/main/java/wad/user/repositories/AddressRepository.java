package wad.user.repositories;

import org.springframework.data.repository.CrudRepository;
import wad.user.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
