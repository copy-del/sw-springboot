package com.smhrd.web.entity;

import java.util.ArrayList;

public class Temp {

	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<>();
		
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		
		
		// for-each가 동작하는 원리 : Iterator 객체가 돌아감
		// 1. Iterator가 list의 크기를 고정(5)
		// 2. list.remove() >> list안에 있는 데이터 삭제, 크기 줄이는 객체가 동작
		// 3. Iterator 와 리스트 삭제 객체가 충돌. ConcurrentException
		// > Java는 멀티스레드 기반의 언어(한번에 여러개의 일을 할 수 있는 언어)이기 때문
		for(String s :list) {
			System.out.println(s);
			list.remove(0);
		}
		// 해결방법 : 순수 for문을 쓰기. 다른 자료형 사용. Concurrent~ 자료구조
		
		
		
		
	}

}
