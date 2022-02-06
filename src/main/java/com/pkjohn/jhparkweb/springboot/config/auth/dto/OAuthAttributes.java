package com.pkjohn.jhparkweb.springboot.config.auth.dto;

import com.pkjohn.jhparkweb.springboot.domain.user.Role;
import com.pkjohn.jhparkweb.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/*
    application-oauth.properties에서 연동 정보 추가 후, 각 소셜서비스별로 로그인을 판단하고 정보를 가져옴.
 */
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes (Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    //Google, Naver 판단
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
       /* else if("kakao".equals(registrationId)) {
            return ofKakao("id", attributes);
        }*/
        return ofGoogle(userNameAttributeName, attributes);
    }

    /*//Kakao
    public static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> id = (Map<String, Object>) attributes.get("id");

        return OAuthAttributes.builder()
                .name((String) id.get("profile_nickname"))
                .email((String) id.get("account_email"))
                .picture((String) id.get("profile_image"))
                .attributes(id)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }*/

    //Naver
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    //Google
    private  static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.USER)    //현재(220206) 로그인 세션 정보를 못읽어오므로 바꿈..
                .build();
    }
    /*
        User 엔티티 생성
        OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때임
        가입할 때의 기본권한을 GUEST로 주기 이해 role 빌더 값에 Role.GUEST을 사용함
     */
}
