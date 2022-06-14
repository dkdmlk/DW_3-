# 통계
1. 통계 테이블을 만듦
>ex) pk날짜 (일일 통계 23:59 or 00:00 에 쿼리를 돌려서 insert)
2. 쿼리로 조회 (v) 

# JavaServerPage = jsp
1. HTML + CSS + JS = HTML
2. HTML + CSS + JS + JAVA = JSP

# 세션
세션 : 서버가 다운되거나 혹은 만료시간이 지나기 전까지 데이터를 보관하는 기술
> 데이터베이스 X 
> 서버(웹서버)에 임시적으로 보관
> 대표적인 세션 : 회원 정보(PK, 이름)

~~~
ResponseBody : return을 json으로

@controller을 하면 return은 페이지 이름으로 해야함.

페이지 이름이 아니라 json으로 return하고
싶을 때 @ResponseBody 사용 
~~~

로그인에 시간을 줄때
~~~
src/main.resources
->
application.properties
->
server.servlet.session.timeout=30
~~~

~~~
DB -> mapper -> Service -> controller(JSP)      -> title name -> WEB
                        -> RestController(ajax) -> JSON -> App(직접DB연결불가하기 떄문에 백엔드 서버사용) & WEB
~~~