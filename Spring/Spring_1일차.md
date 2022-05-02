# Spring 배우는 이유?
-> Spring은 검증된툴이며 검증되었기에 회사에서 사용 교육기관에서도 Spring을 베이스로 교육

결론 : 수요가 많음

## Spring이란?
Java로 다양한 어플리케이션(웹 or 앱)을 만들기 위한 *틀(플레임 워크)

+ 플랫폼(Spring) -> 플레임워크

## Spring 종류
1. Spring 레거시(일반 Spring)
2. Spring boot(경량 Spring)
3. Spring 전자정부프레임워크(egov)

## Spring과 같이 사용하는 프로그램
->Spring 만으로 어플리케이션을 개발하는데 한계 / 데이터베이스, 고객에게 보여줄 화면 필요 등등...
1. 데이터 베이스 연결과 데이터 생성, 호출, 삭제, 수정을 도와주는 ORM
>  - MyBatis(V), JPA
2. 필요한 기능을 쉽게 다운로드받을 수 있고, 서버에 옵로드(배포)를 도와주는
빌드관리 도구(Build Tool)
>  - Gradle(V), Maven
3. 화면을 도와주는 템플릿 엔진!
>  - JSP, Thymeleaf(V)
4. 속성(데이터베이스 주소,아이디,비번) 정의를 담당하는 속성 파일!
> - yaml(야믈)(V), properties
5. 서버를 담당하는 Tomcat!
6. (optional) 그 외 Docker(도커), graphQL(그래프 큐엘) 등등 ...
## 여러가지 언어툴
+ javascript ->node.js
+ python -> flask, 장고
+ c -> asp
+ java -> spring

## ***이클립스 or Sts 설정 (IDE)
1. 한글 설정
> - window -> Preferences ->encoding검색->General(Workspace 선택)->Text file encoding -> other -> UTF-8
2. 프레임워크생성
+ https://start.spring.io/

(약속) -> 패키지만들기(3개) -> 첫번재이름.두번재이름.3번재이름(com.naver.www) ex)com.회사이름.프로젝트명
1. 첫번 째 패키지: www.io.kr 등등
2. 두번 째 패키지 : 회사이름
3. 세번 째 패키지 : 프로젝트명

1. controller 패키지생성
controller : url 요청을 받는곳
2. http?
+ GET : 요청, 조회

~~~ java
package com.example.first_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller: url을 요청받는 곳
@RestController
public class MainController {
	@GetMapping("/index")
	public String call() { // index라는 주소를 요청하면 call()메소드를 실행~
		String word = "강인석 Hello World!";
		return word;
	}
	@GetMapping("/sum")
	public int callSum() {
		int x = 10;
		int y = 100;
		return x+y;
	}
	@GetMapping("/array")
	public int[] callArray() {
		int array[] = {1,5,9,15,254};
		return array;
	}
}
~~~

## 실시간 main 
-> devtools gradle

compileOnly("org.springframework.boot:spring-boot-devtools")

-> build.gradle 오른쪽클릭 gradle -> 다운로드

## 이클립스에서 인식을 못하는 프로그램
+ http://projectlombok.org/p2
Help - > Install -> http://projectlombok.org/p2 입력