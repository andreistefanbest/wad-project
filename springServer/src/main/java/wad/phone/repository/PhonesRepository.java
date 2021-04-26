package wad.phone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wad.phone.entity.Phone;


public interface PhonesRepository extends MongoRepository<Phone, String> {
}
