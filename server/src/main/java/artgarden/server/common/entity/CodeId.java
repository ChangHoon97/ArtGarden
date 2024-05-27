package artgarden.server.common.entity;

import java.io.Serializable;
import java.util.Objects;

public class CodeId implements Serializable {

    private String cdtype;
    private String code;

    // 기본 생성자
    public CodeId() {}

    // 필드를 초기화하는 생성자
    public CodeId(String cdtype, String code) {
        this.cdtype = cdtype;
        this.code = code;
    }

    // equals와 hashCode 메서드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeId codeId = (CodeId) o;
        return Objects.equals(cdtype, codeId.cdtype) && Objects.equals(code, codeId.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cdtype, code);
    }

    // getters and setters (생략 가능)
}
