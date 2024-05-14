package org.unlogged.springwebfluxdemo.model.ecommerce;

public class ProductReviewDto {
    private String reviewId;
    private String comment;
    private String reviewerName;
    private int rating;

    public ProductReviewDto(String reviewId, String comment, String reviewerName, int rating) {
        this.reviewId = reviewId;
        this.comment = comment;
        this.reviewerName = reviewerName;
        this.rating = rating;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ProductReviewDto{" +
                "reviewId='" + reviewId + '\'' +
                ", comment='" + comment + '\'' +
                ", reviewerName='" + reviewerName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
