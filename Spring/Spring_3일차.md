# DB연동
~~~
  .   ____          _           
 /\\ / ___'_ __ _ _(_)_ __  __ _ 
( ( )\___ | '_ | '_| | '_ \/ _` | 
 \\/  ___)| |_)| | | | | || (_| |  
  '  |____| .__|_| |_|_| |_\__, |
 =========|_|==============|___/=
~~~
## MVC
M
+ 비즈니스로직 -> service
+ DB로직 -> mapper

Spring<->DB ORM(화살 표를 도와주는 역할) : 데이터베이스 연결과 데이터 생성,호출,저장,삭제를 담당하는 ORM!
- MyBatis
- JPA
1. Spring ->DB(Mybatis) 
2. DB -> Spring (return VO)

## 속성(데이터베이스 주소,아이디,비번) 정의를 담당하는 속성 파일!
- yaml(야믈)(V), properties
src/main/resources -> 에 properties가 들어가있다.

properties or yaml 에는 java를제외한 (HTML,CSS,SQL등이 들어있다.)

properties 확장명 yaml -> Help -> yaml 설치

+ resources에 sqlmap폴더(패키지)생성 -> sqlmap패키지에서 new -> other -> XML파일 생성 파일이름(sqlmapper_테이블이름.xml)생성

~~~yaml
#MyBatis 설정
mybatis:
   mapper-locations: classpath:/sqlmap/sqlmapper_*.xml (위치설정)
   type-aliases-package: com.example.first_spring.vo(vo패키지 경로)
#MySQL 접속정보 설정
spring:
   datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver 
      url: jdbc:mysql://localhost:3306/dw?useUnicode=true&charaterEncoding=utf-8&serverTimezone=UTC ()
      username: (sql username)
      password: (sql 비밀번호)
~~~
비밀번호 유저네임 확인 ->dbver localhost 오른쪽클릭 ->에디트 커넥션 클릭

~~~Xml
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- MyBatis에게 Mapper 경로를 적어야 한다. -->
<mapper namespace="com.example.first_spring.mapper.MainMapper">
	<select id="getEmpList" resultType="EmpVO">
		SELECT
			empno,
			ename,
			job
		FROM emp
	</select>
</mapper>
~~~

