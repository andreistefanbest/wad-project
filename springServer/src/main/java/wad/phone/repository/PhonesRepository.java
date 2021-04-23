package wad.phone.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import wad.phone.entity.Phone;

@Repository
public interface PhonesRepository extends ReactiveMongoRepository<Phone, String> {
}
