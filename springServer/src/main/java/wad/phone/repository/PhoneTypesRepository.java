package wad.phone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wad.phone.entity.PhoneType;


public interface PhoneTypesRepository extends MongoRepository<PhoneType, String> {
}
