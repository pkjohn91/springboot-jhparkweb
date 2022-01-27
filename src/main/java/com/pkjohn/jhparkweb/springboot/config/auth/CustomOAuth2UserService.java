package com.pkjohn.jhparkweb.springboot.config.auth;

import com.pkjohn.jhparkweb.springboot.config.auth.dto.OAuthAttributes;
import com.pkjohn.jhparkweb.springboot.config.auth.dto.SessionUser;
import com.pkjohn.jhparkweb.springboot.domain.user.User;
import com.pkjohn.jhparkweb.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

/*
    구글 로그인 이후 가져온 사용자의 정보(email, name, picture 등)를 기반으로 가입 및 정보수정, 세션 저장 등의 기능을 지원
 */
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser (OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        /*
            registrationId: 현재 로그인 진행 중인 서비스를 구분하는 코드, 네이버/구글/카카오 등 로그인 시 구분하기 위함.
            userNameAttributeName: OAuth2 로그인 진행 시 키가 되는 필드값을 의미, pk와 같은 의미
                                   구글은 기본적으로 코드를 지원("sub")하지만 네이버, 카카오 등은 기본 지원하지 않음..
                                   이후 네이버 로그인과 구글 로그인 동시 지원할 때 사용
        */
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        /*
            OAuthAttributes: OAuth2UserService를 통해 가져온 Oauth2User의 attribute를 담을 클래스
                             이후 네이버 등 다른 소셜로그인도 이 클래스 사용
            SessionUser: 세션에 사용자 정보를 저장하기 위한 dto 클래스
         */
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        // of() : OAuth2User에서 반환하는 사용자 정보가 Map이기 때문에 값 하나하나를 변환해주어야 한다.
        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                                                                                        attributes.getAttributes(),
                                                                                        attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                                                    .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                                                    .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
