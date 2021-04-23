package wad.review.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import wad.review.entity.Review;

public interface ReviewRepository extends ReactiveMongoRepository<Review, String> {
    Flux<Review> findByPhoneId(String phoneId);
}
