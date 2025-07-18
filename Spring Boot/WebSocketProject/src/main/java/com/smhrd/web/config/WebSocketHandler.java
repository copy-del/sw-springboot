package com.smhrd.web.config;

import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


//@Controller	// > 스프링 컨테이너에서 해당하는 파일을 컨트롤러로 인식하고, 자동으로 로딩하기 위해서
//@ServerEndpoint("/websocket")	// 통신의 끝지점. 고유 url필요
public class WebSocketHandler extends TextWebSocketHandler{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	// 소켓에 접속한 사용자들을 관리하는 자료구조
	
	private static final HashMap<String,WebSocketSession> CLIENTS = new HashMap<>();
	
	
	// 웹 소켓에 접속했을 때, 실행하는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// session : 웹 소켓에 접속한 사용자에 대한 정보를 담고있는 객체
		// 사용자 한명을 구분할 수 있는 id 값이 필요함
		logger.info("접속성공 > "+ session.getId());
		CLIENTS.put(session.getId(), session);
	}
	
	// 웹 소켓이 종료되었을 때, 실행하는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	
		CLIENTS.remove(session.getId());
	}

	// 웹 소켓의 텍스트 메시지 전달 시 실행하는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 1. 해당하는 메세지를 보내는 사용자 정보를 구분
		String id = session.getId();
		// 2. 보낸 사용자를 제외한 모든 사용자에게 메세지를 send
		CLIENTS.entrySet().forEach(data -> {
			if(data.getKey() != id) {
				// 이때 메세지를 다른 사용자에게 보내주기
				try {
					data.getValue().sendMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}


	
}
