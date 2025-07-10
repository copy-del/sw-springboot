package com.smhrd.web.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.web.entity.Board;
import com.smhrd.web.entity.SearchCriteria;

@Mapper
public interface BoardMapper {

//	게시글을 전체 조회하는 메소드
	public ArrayList<Board> selectBoard();

	public void postBoard(Board board);

//	게시글 하나 가져오기
	public Board selectOne(int bnum);
	
	public void updateCount(int bnum);
//	기준이 내용에 검색한 글자가 '포함'되어있는 모든 게시글을 조회해주는 쿼리
	public List<Board> search(SearchCriteria cri);


}
