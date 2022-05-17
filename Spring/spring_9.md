# jsp
jsp는 html 안에서 java코딩이 가능한 파일

폴더생성(board 왼쪽클릭후 오른쪽클릭 -> source 폴더 클릭 -> src/main/webapp생성 -> WEB-INF폴더 생성 -> views생성 -> index.jsp생성)

js, css, images폴더생성(resources 폴더에 static 폴더 에서 js, css, images생성 후 yaml파일 에서 지정)

## 폴더생성
![jsp설정](jsp%EC%84%A4%EC%A0%95.PNG)

## jsp
~~~jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello World!</h1>
</body>
//script js 폴더에서 불러오기 src(주소)
<script type="text/javascript" src="/resources/static/js/index.js"></script>
</html>
~~~
jsp 생성시 자동으로 설정되어 있다.

## yaml파일
~~~yaml
#제목과 버전 설정
application:
   version: v0.0.1
   title: KANGINSEOK

#MyBatis 설정
mybatis:
   mapper-locations: classpath:/sqlmap/sqlmapper_*.xml
   type-aliases-package: com.dw.board.vo
#MySQL 접속정보 설정
spring:
   datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/dw?useUnicode=true&charaterEncoding=utf-8&serverTimezone=UTC
      username: root
      password: rkddlstjr
   #jsp 경로 설정
   mvc:
      view:
         prefix: /WEB-INF/views/
         suffix: .jsp
      #css,js,images 폴더 위치 설정
      static-path-pattern: /resources/static/**
~~~

## build.gradle
~~~java
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-web'
	//ORM
	implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2'
	//lombok
	compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'
	//devtools
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	//MYSQL
	runtimeOnly 'mysql:mysql-connector-java'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	//JSP 다운로드
	implementation "org.apache.tomcat.embed:tomcat-embed-jasper"
	implementation 'javax.servlet:jstl'
}
~~~

## controller

~~~java
package com.dw.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping("/home")
	public String callHomepage() {
		return "index";
	}
}

~~~