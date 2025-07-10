package com.smhrd.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration // 환경설정 파일 스프링 컨테이너가 생성할 수 있게 끔
@EnableWebSocket // 웹 소켓 활성화 시킬것
public class WebSocketConfig implements WebSocketConfigurer{

	// 우리가 직접 컨테이너가 관리할 수 있게끔
	// 웹소켓핸들러를 bean 형태로 생성
	// 웹소켓핸들러를 관리하는 레지스트리에 추가
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/websocket").setAllowedOrigins("*");
	}

	@Bean
	public WebSocketHandler webSocketHandler() {
		return new WebSocketHandler();
	}
}
