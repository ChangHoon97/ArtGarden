package artgarden.server.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@IdClass(CodeId.class)
public class Code {

    @Id
    private String cdtype;

    @Id
    private String code;

    private String cdnm;
    private String cdengnm;
    private String cddesc;
    private int cddepth;
    private int orderby;
    private boolean useyn;
    private boolean delyn;
    private String uppcd;
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;
    private String remark5;
    private String regid;
    private LocalDateTime regdt;
    private String updid;
    private LocalDateTime upddt;


}
