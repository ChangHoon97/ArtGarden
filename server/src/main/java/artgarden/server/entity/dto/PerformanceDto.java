package artgarden.server.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

public class PerformanceDto {

    @Getter
    @AllArgsConstructor
    public static class Api{
        private String id;
        private String name;
        private Date startDate;
        private Date endDate;
        private String place;
        private String time;
        private String age;
        private String price;
        private String casting;
        private String production;
        private String genre;
    }



}
