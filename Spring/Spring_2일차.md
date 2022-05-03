## controller 에 문제를 많이 풀때 생기는일
1. 문제를 풀 때 마다 controller 에 코드가길어짐

2. 유지보수가 힘들어짐

3. 문제를 해결하기위해 *MVC 아키텍쳐 가 생겨남

## MVC(Model-View-Controller)아키텍쳐
-> 프로그래밍 로직을 서로 영향 없이 쉽게 고칠수 있는 소프트웨어 디자인 패턴

+ M : Model  
>+ -> 비즈니스 로직을 구현!, DB 연동 로직

+ V : View
>+ -> 최종 사용자에게 결과를 화면(UI)으로 보여줌

+ C : Controller
>+ -> 요청을 받아서 화면과 Model를 연결시켜주는 역할

~~~java
public class MainController {
package com.example.first_spring.controller;
	@Autowired //Spring이 해당 객체(클레스)를 관리해줌.IoC(Inversion of control)
	MainService service;

    @GetMapping("/userList")
	public List<UserVO> callUserList(){
		List<UserVO> list = new ArrayList<UserVO>();
		list = service.getUserList();
		return list;
	}
-------------------------------------------------------------    
package com.example.first_spring.service;
@Service
public class MainService {
	public List<UserVO> getUserList(){
		List<UserVO> list = new ArrayList<UserVO>();
		list.add(new UserVO("홍길동", 51, "서울"));
		return list;
	}
}
~~~

# Spring 3대장 개념

+ IoC(Inversion of control)
>- 객체의 생성부터 소멸까지 Spring에서 관리함

+ DI(Dependency injection)
>  - Spring이 객체(클래스)가 필요하다면 외부에서 가져다가 주입하는 방식. 
>  - (객체(클래스))를 외부에서 가져다가 쓰기떄문에 new 연산자가 사라짐.)

+ AOP(Aspect Oriented Programming) 관점지향프로그래밍
> - 여러 메소드(함수) 에서 중복되는 코드가 있다면, 따로 모아서 재활용 하는것.

## .
@service는 비즈니스 로직, 계산 수행, 외부 API 호출을 수행하는 Java클래스를 표시

@controller느 URL 요청이 오면 응답담당

1. 클래스를 만들면 어노테이션 부터 만들자
2. @Autowired : 객체생성과 소멸을 Spring이 관리