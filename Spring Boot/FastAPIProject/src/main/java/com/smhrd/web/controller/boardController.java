package com.smhrd.web.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smhrd.web.entity.Board;
import com.smhrd.web.entity.SearchCriteria;
import com.smhrd.web.mapper.BoardMapper;

@Controller		// Spring Container가 해당하는 파일을 scan할 수 있게끔 해주는 annotation
public class boardController {
	
//	데이터 흐름을 체크할 수 있는 log찍는 도구 꺼내오기
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired // container에 자동으로 생성된 객체를 연결하는 annotation
	BoardMapper mapper;

// ~ Application (main method를 가지고 있는 파일)
//	하위 패키지로 파일을 생성해야한다.
//	FC(프론트 컨트롤러)가 업무를 지시하는 역할을 하는 일반 자바클래스(POJO)
//	해당하는 파일을 통해서만, jsp or html문서를 화면에 띄울 수 있다.
	
//	Spring boot는 RequestMapping을 선호하지 않는다. 정확한 전송방식을 지정하는 것을 권장
	@GetMapping("/")
	public String board(Model model) {
//		Model : request 경량화 버전
//		내가 직접 생성할 필요없이, 매개변수로 받아오면 알아서 container로 전달
	
	
//		1. DAO 생성
//		BoardDAO dao = new BoardDAO();
//		2. DAO 사용
		List<Board> boardList = mapper.selectBoard();
//		3. 결과를 받아와서, request 영역에 데이터 바인딩
		model.addAttribute("boardList", boardList);
		
		return "board";	// 이동해야 할 페이지
		
		
	// ViewResolver의 논리적인 주소값을 물리적인 주소값으로 변환
//	prefix + board + suffix 
//	forward 방식 : 요청 1 응답 1
//	 > 초기 요청 url이 변하지 않고, 요청 객체가 다음 페이지까지 유효하다.
//	redirect : 요청 2 응답 2
	}
	
	
	// 글쓰기 페이지를 열어 줄 수 있는 메소드
	@GetMapping("/insert")
	public String insert() {
		return "insert";
	}
	
	@PostMapping("/insert")
	public String insert(Board board) {
		// 1. DB에 데이터 삽입하기
		mapper.postBoard(board);
		logger.info("데이터 확인 >> "+board);
		return "redirect:/";
//		return "redirect:/board";
	}
	
	@GetMapping("/selectOne/{bnum}")
	public String selectOne(@PathVariable("bnum") int bnum, Model model) {
		Board b = mapper.selectOne(bnum);
		logger.info("수집데이터"+bnum);
		model.addAttribute("b", b);
		return "selectOne";
	}
	
	@GetMapping("/updateCount")
	@ResponseBody // 페이지를 이동하는 메소드가 아니라 비동기 통신을 처리하는 메소드임을 나타내는 annotation
	public void updateCount(@RequestParam("bnum") int bnum) {
		logger.info("count데이터 수집 : "+bnum);
		mapper.updateCount(bnum);
	}
	
	@GetMapping("/search")
	public String search(SearchCriteria cri, Model model) {
		logger.info("검색기준 수집 >>"+cri);
		// 1. DB에서 검색한 게시글들을 조회
		List<Board> boardList = mapper.search(cri);
		// 2. scope 담아주고, 페이지 조회
		model.addAttribute("boardList", boardList);
		return "board";
	}
	
	@GetMapping("/temp")
	public String temp(RedirectAttributes rttr) {
		// redirect로 이동했을 때, 마지막 이동하는 페이지에 수집한 데이터를 전송하고 싶다면 rttr을 사용한다.
		rttr.addAttribute("temp", "temp");
		// rttr : model interface를 구현하고 있기 때문에 
		// 		  2번째 저장할 수 있는 매개변수가 Object라고 작성은 되어있으나
		// 		  문자열만 저장 가능하다.
		return "redirect:/";
	}
	
	@GetMapping("/crawling")
	public String crawling() {
		return "crawling";
	}
	
	
	
	
}	
