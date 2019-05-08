package wad.review;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.review.dto.ReviewDTO;
import wad.review.entities.Review;
import wad.review.entities.ReviewRepository;
import wad.user.entities.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ReviewDTO> getReviews(Integer phoneId) throws Exception {
        return StreamSupport.stream(reviewRepository.findAll().spliterator(), false)
                .filter(review -> review.getPhoneId().equals(phoneId))
                .map(this::dtoFromReview)
                .collect(Collectors.toList());
    }

    @Override
    public void addReview(Review r) throws Exception {
        reviewRepository.save(r);
    }

    private ReviewDTO dtoFromReview(Review r) {
        ReviewDTO dto = new ReviewDTO();
        BeanUtils.copyProperties(r, dto);
        if (r.getUserId() == null) {
            dto.setUserName(null);
            return dto;
        }

        dto.setUserName(userRepository.findById(r.getUserId()).get().getFullName());

        return dto;
    }
}
