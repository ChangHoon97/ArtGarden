package artgarden.server.review.entity.dto;

import java.time.LocalDateTime;

public interface ReviewListDTO {

    Long getReviewid();
    String getPerformid();
    String getContent();
    Double getRate();
    String getMemberid();
    String getRegid();
    LocalDateTime getRegdt();
    String getUpdid();
    LocalDateTime getUpddt();
    String getPosterurl();
    String getName();
}
