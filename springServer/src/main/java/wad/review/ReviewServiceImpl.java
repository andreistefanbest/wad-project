package wad.review;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.review.dto.ReviewDTO;
import wad.review.entities.Review;
import wad.review.entities.ReviewRepository;
import wad.user.entities.UserRepository;
import wad.utils.GenericService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenericService genericService;

    @Override
    public List<ReviewDTO> getReviews(Integer phoneId) {
        return genericService.fetchEntities(reviewRepository)
                .stream()
                .filter(review -> review.getPhoneId().equals(phoneId))
                .map(this::dtoFromReview)
                .collect(Collectors.toList());
    }

    @Override
    public void addReview(Review r) {
        reviewRepository.save(r);
    }

    private ReviewDTO dtoFromReview(Review r) {
        ReviewDTO dto = new ReviewDTO();
        BeanUtils.copyProperties(r, dto);

//        userRepository.findById(r.getUserId())
//                .ifPresent(user -> dto.setUserName(user.getFullName()));

        genericService.fetchEntities(userRepository)
                .stream()
                .filter(u -> u.getUserId().equals(r.getUserId()))
                .findFirst()
                .ifPresent(user -> dto.setUserName(user.getFullName()));
        return dto;
    }
}
