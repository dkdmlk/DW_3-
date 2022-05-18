# 개발순서
1. REST API 개발 백엔드와 프론트를 구별
>
2. 템플릿엔진으로 개발 결과를 jsp에서 봐야함

## error
404 : 발생 이유
1. 없는 url 입력
2. url 오타
3. @RestController OR @Controller 어노테이션 입력X

405 : HTTP 메소드 오류
1. get인데 post데이터 전송(HTTP 메소드 불일치)

500 : 백엔드에서 에러
1. 자바코딩을 잘못
>>ex)배열길이가 10인데, 11번재 인덱스를 호출
>>+ 쿼리오타 ->쿼리 결과 List인데 List로 안 받을 때

403 : 권한 에러 

# 암호화
암호화 하는 방법
1. 단방향 암호 (복호화 불가능)

2. 양방향 암호 (복호화)

-- 양방향 복호화 방법
> public key 를 가진 사람만 해독할 수 있음.

+ 암호는 어떤 수학전 알고리즘으로 암호화가 됨 
>+ 대표 알고리즘 : SHA256 

Spring 에서 사용하는 암호

Spring security (보안 관련된 기능 패키지)
+ gradle에 Spring security다운로드 ->com.회사이름.프로젝트명.conf 패키지 -> WebSecurityCongfig 생성
~~~java
bulid-gradle
implementation("org.springframework.boot:spring-boot-starter-security")
등록

package com.dw.board.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityCongfig extends WebSecurityConfigurerAdapter{
	@Bean
	public PasswordEncoder getpasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		http.csrf().disable()
		.formLogin().disable();
	}
}

Service
//학생 생성
	@Transactional(rollbackFor = Exception.class) 
	public int setStudents(StudentsVO VO) {
		//학생 비밀번호 암호화
		String password = VO.getStudentsPassword(); //get password를가져와
		password = passwordEncoder.encode(password);//password를 암호화
		VO.setStudentsPassword(password);//암호화된 페스워드를 set입력
		return StudentsMapper.insertStudent(VO);
	}
~~~

+ Spring이 관리해주는 클래스를 bean(빈)이라고 한다.  Spring 이 객체 생성과 소멸을 관리


