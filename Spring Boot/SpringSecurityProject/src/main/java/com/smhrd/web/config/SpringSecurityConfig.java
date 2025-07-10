package com.smhrd.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration // 컨테이너에 들어가거라
@EnableWebSecurity // WebSecurity가 동작할 수 있게끔 설정
//					해당하는 환경설정 파일이 websecurity 환경설정 파일이다.
public class SpringSecurityConfig {
	
//	권한 허용 메소드 3가지
//	1. permitAll() : 요청 전부 허용
//	2. hasRole(역할) : 역할에 맞는 사람만 허용
//	3. authenticated() : 인증된 사용자만 허용	
	
	// 나만의 커스터마이징 시큐리티 필터 추가
	@Bean()
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		예전 방식 : http.cors().disable().csrf().disable().~(). > 권장하지 않는 방식
//		요즘 방식 : http.cors(data -> {}) 람다식으로 사용하는 것을 권장
//		AbstractHttpConfigurer::disable
//		:: >> 메소드를 레퍼런스 삼겠다.
//		1. CORS 옵션을 비활성화.
		http.cors(AbstractHttpConfigurer::disable)
//		2. Cross Site Request Forgery 옵션을 비활성화.
			.csrf(AbstractHttpConfigurer::disable)
//		3. 요청에 대한 권한 설정.
			.authorizeHttpRequests(request -> {
//				3-1. forward 방식일 때, FC 접근허용.
				request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//						3-2. 매개변수로 들어간 url에 대해서는 접근 허용
						.requestMatchers("/join","/imgs/**","/join-process").permitAll()
						.requestMatchers("/adminPage").hasRole("admin")
						.requestMatchers("/userPage").hasAnyRole("users","admin")
//						3-3. 어떤 요청이든 인증된 사용자만 접근 허용
						.anyRequest().authenticated();
			})
//			4. 내가 원하는 인증 페이지로 넘기고 싶을 때, 설정
			.formLogin( login -> {
				login.loginPage("/")
				.loginProcessingUrl("/login-process")
//				인증받을 사용자 정보를 mapping
				.usernameParameter("id")
				.passwordParameter("pw")
				.defaultSuccessUrl("/main",true)
				.permitAll();
			});
			
		
		
		return http.build();
	}
	
//	DB에 있는 사용자 정보를 가져오려면 기본적으로 암호를 복호화할 수 있는 Encoder가 필요함
	@Bean()
	public PasswordEncoder passwordEncoder() {
//		BCryptPasswordEncoder : 잘 만들어진 인코더
		return new BCryptPasswordEncoder();
//		내가 커스터마이징한 encoder를 쓰고 싶다면, 따로 파일을 작성
	}
	
	
}
