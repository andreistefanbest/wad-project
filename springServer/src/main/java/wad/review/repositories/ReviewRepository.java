package wad.review.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import wad.review.entities.Review;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, Integer> {
    List<Review> findByPhoneId(Integer phoneId);
}
