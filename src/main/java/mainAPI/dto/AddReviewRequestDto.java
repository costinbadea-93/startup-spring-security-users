package mainAPI.dto;

import mainAPI.model.User;

public class AddReviewRequestDto {

    public ReviewDto review;
    public User user;

    public ReviewDto getReviewDto() {
        return review;
    }

    public void setReviewDto(ReviewDto reviewDto) {
        this.review = reviewDto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
