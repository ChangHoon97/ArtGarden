package artgarden.server.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SnsType {
    GOOGLE("SNS_GOOGLE", "구글회원"),
    KAKAO("SNS_KAKAO", " 카카오회원"),
    LOCAL("SNS_LOCAL", "로컬회원(사이트가입)");

    private final String key;
    private final String title;
}
