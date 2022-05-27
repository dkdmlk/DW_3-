# Spring
## 1.java로 웹을 개발하는 방법이 크게 2가지.
- 프레임워크(==플랫폼)를 이용한 웹개발
- 프레임워크를 이용한 웹개발 X(JSP&서블릿)

## 2.프레임워크 종류 (자바기준)
- Spring boot(V) 내장톰캣
- Spring + (톰캣)
- Spring egov(우리나라)

## 3.빌드 툴
- Gralde (V)
- Maven (Maven은 XML)

# 세션
특별한 정보를 **서버에 **저장

HTTP <- 정보를 유지하는 기능X

## HTTP 통신 특징
1. connectionless(비연결지향)
>>- 더이상 연결을 지속적으로 하지 않음
2. Stateless(상태정보유지 안함)
>>- 요청을 응답하고 접속을 바로 끊어서 정보를 저장하지 않는다.
- ex)카톡보내고 카톡방 나감

이러한 HTTP 이슈 때문에 쿠키와 세션을 이용해서 간단한 정보를 임시로 서버에 저장한다.

# 쿠키
쿠키는 웹브라우저(크롬,사파리,웨일...)에 저장
- 프론트(javasscript)에서 저장
>>- 보안 낮음(세션이랑 비교했을때)

세션은 웹서버에 저장
- 세션은 백엔드(java)에 저장
>>- 보안 높음

# root
root란 프로그래밍에서 최고 권한을 의미

root 권한을 가져서 데이터베이스 생성, 삭제 테이블 생성,삭제가 가능했음.

대부분 회사에서 DML(SELECT, UPDATE, INSERT, DELETE)만 가능한 계정을 줌 

~~~sql
-- 컬럼을 추가,수정,삭제하고 싶을 때 사용
 alter table board add column cnt integer(4) default 0;

-- root 계정이 계정을 생성함
-- '%' 모든 IP허용
create user (id)@'%' identified by '123';

-- grant로 권한 부여
-- on 데이터베이스이름.테이블이름(dw.*)
grant select ,insert ,update on dw.* to inseok@'%';
~~~

# 프로젝트때 DB공유
Edit 계정 사용할 private 아이피 -> 만든 계정으로 로그인

- private IP

같은 네트워크에 있는 private IP끼리는 public IP 몰라도 통신 가능

- public IP

다른 네트워크에 있는 private ip랑 통신할때는 public IP 필요

>- server : 서비스제공
>>- (DB서버,웹서버)
>- 포트번호(PK) : 서비스 식별 번호
>>- 포트번호 범위 : 0 ~ 65535 (추천커스텀 포트번호 : 3000~9999)

## 키보드 동작 방식
1. 키보드를 누를 때 -> keydown
2. 누른 키를 뗄 때 -> key up
3. 키보드를 계속 누르고 있을 때 -> keypress