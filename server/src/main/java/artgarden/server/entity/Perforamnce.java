package artgarden.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class Perforamnce {

    @Id
    private String id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String place;
    private String time;
    private String age;
    private String price;
    private String cast;
    private String production;
    private String genre;

}
