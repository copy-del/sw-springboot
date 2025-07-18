package com.smhrd.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.web.entity.DataMember;

@Repository
public interface DataMemberRepository extends JpaRepository<DataMember, String> {
// JPA 사용시 대상이되는 테이블 내부 PK 가 정수형이라면?
//	<> : 제네릭기법을 사용하기 때문에, int(X) > int의 객체형태인 Integer or Long 사용
	
	// Hibernate 구현체를 사용하는 용도로 만든 도구
	
//	hibernate가 메소드 명칭을 보고 sql 구문 제작
//	기본으로 제공되는 메소드
//	1. find() : select * from ~  > 조회시 사용
//	2. findById() >> PK를 기준으로 조회  *** 여러개 단어를 하나로 작성할 때, 반드시 카멜식 기법.
//	3. save(DataMember m) : insert 구문 / 데이터 수정(update)
//		id값을 기준으로 봤을 때, 데이터가 존재한다 > update, 존재하지 않는다 > insert
//	4. delete(String id) : 데이터 삭제.
	
//	나만의 커스터마이징 규칙을 만들고 싶다면 JPA 규칙을 지켜야한다.
//	find... : select 구문 동작 > ... 컬럼이름.
//	By : where절. 조건 부여
//	테이블의 컬럼이름들을 카멜식으로 표현 > 조건절에 들어오는 매개변수를 낱개로 개수 매칭해서 받아와야함.
//	ex) id와 pw가 일치한 데이터 조회
	public DataMember findByIdAndPw(String id, String pw);
	
	
}
