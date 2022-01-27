package com.pkjohn.jhparkweb.springboot.config.auth;

import com.pkjohn.jhparkweb.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //스프링 시큐리티 설정들을 활성화, 기본적으로 csrf의 공격을 방지하고 있다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들 disable
                .and()
                    .authorizeRequests() //URL별 권한 관리를 설정하는 옵션의 시작점, 이게 선언되어야 antMatchers 옵션 사용 가능
                    .antMatchers("/","/css/**","/images/**","/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
        /*
            .csrf() : Cross site request forgery란 웹사이트의 취약점을 이용하여 이용자가 의도하지 않은 요청을 통한 공격을 의미한다.
                        즉 CSRF 공격이란, 인터넷 사용자(희생자)가 자신의 의지와는 무관하게 공격자가 의도한 행위(등록, 수정, 삭제 등)를 특정 웹사이트에 요청하도록 만드는 공격이다.
                        ex) 2008년 옥션 개인정보 유출 사건, 은행 계좌이체 등..

            .antMatchers() : 권한 관리 대상을 지정하는 옵션임
                             URL,HTTP 메소드별로 관리가 가능
                             "/"등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 줌.
                             "/api/posts/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 했음.

            .anyRequest() : 설정된 값들 이외 나머지 URL들을 나타냄
                            여기선 authenticated()을 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용하도록 함.
                            인증된 사용자는 즉, 로그인한 사용자들을 의미.

            .logout().logoutSuccessUrl("/") : 로그아웃 기능에 대한 여러 설정의 진입점.
                                              로그아웃 성공 시 "/" 주소로 이동

            .oauth2Login() : OAuth 2 로그인 기능에 대한 여러 설정의 진입점

            .userInfoEndpoint() : OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당

            .userService() : 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                             리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있음.
         */

    }
}
