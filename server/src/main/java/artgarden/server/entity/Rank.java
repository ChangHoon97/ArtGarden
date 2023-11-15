package artgarden.server.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private LocalDate rankDate;
    private int rankNum;
    private String performId;
}