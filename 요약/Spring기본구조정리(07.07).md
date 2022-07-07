# 1.Spring java 구조

## 1-1. src/main/java

```java
com.(프로젝트이름).(프로젝트)// 실행파일이 들어있다.
com.(프로젝트이름).(프로젝트).controller //controller를 모아두는 패키지 - service에서는 받아온 정보를 mapping(주소) 사용하기위한 class
com.(프로젝트이름).(프로젝트).service // service를 모아두는 패키지 - mapper에서 받아온 정보를 가지고 필요한 로직를 짜는 class
com.(프로젝트이름).(프로젝트).mapper // mapper를 모아두는 패키지 - xml(mybatist)에서 작성한 쿼리를 받아오는 역할(mapper는 intrepace)
com.(프로젝트이름).(프로젝트).vo // vo를 모아두는 패키지 - DB의 커리를 불러오고 수정하기위한 class
com.(프로젝트이름).(프로젝트).utlis // 기능을 모아두는 class (pageHandle[페이징구조프로그램],리캡차,통개등등 기능)
com.(프로젝트이름).(프로젝트).config // 설정이나 프로그램의 실행 일부 등을 저장해둔 파일(Spring Security,log 대상제외등등)
```

## 1-2. src/main.resources

```java
sqlmap
->sqlmapper_(테이블이름).xml //mapper설정후 sql쿼리정보를 보낸다(MyBatis)
static
-> css,js 등 이 들어있다.
.yaml & .properties //데이터베이스 주소,아이디,비번들을담당
//yaml은 개층구조 ,properties 가로
---//그 외 사용에도움을주는 프로그램
init.sql // 시작할때 테이블&쿼리가없다면 생성해준다
logback.xml//실행시 오류 등 로그를보내준다
```

## 1-3 src/test/java

기본 자바와같이 바로실행결과를 볼수있으며 데이터업로드(mapper를 override)해서 볼수 있다.

## 1-4 src/main/webapp

```java
webapp
->WEB-INF
    ->biews
        .jsp 파일
        및 필요에따른 폴더(include,error폴더)
```

## 1-5 Project 종류

```java
1-5-1. gradle
-> bulid.gradle
//구조
plugins {//
	id 'org.springframework.boot' version '2.6.8' //spring boot 버전
	id 'io.spring.dependency-management' version '1.0.11.RELEASE'
	id 'java'//사용언어
    id 'war'//배포를하기위해 압축종류
}
dependencies { //사용하기위한 툴(저장받을 프로그램)
	//Spring security
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2'
	//Session
	implementation 'org.springframework.session:spring-session-core'
	compileOnly 'org.projectlombok:lombok'
	//devtools
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	//MySQL
	runtimeOnly 'mysql:mysql-connector-java'
	//lombok
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
	//JSP다운로드
	implementation "org.apache.tomcat.embed:tomcat-embed-jasper"
	implementation 'javax.servlet:jstl'
	//PageHanlder
	implementation group: 'com.github.pagehelper', name: 'pagehelper-spring-boot-starter', version: '1.4.2'
	//excel
	implementation group: 'org.apache.poi', name: 'poi-ooxml', version: '5.2.2'
	//	리캡차 사용을 위한 json
	implementation 'javax.json:javax.json-api:1.1.2'
	implementation 'org.glassfish:javax.json:1.1'
}
1-5-2. maven
~~~java
maven
-> pom.xml
//구조
    <parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.0</version>//버전
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

    <dependencies>//사용하기위한 툴(저장받을 프로그램)
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<!--JSP-->
		<dependency>
		    <groupId>org.apache.tomcat.embed</groupId>
		    <artifactId>tomcat-embed-jasper</artifactId>
		</dependency>
		<dependency>
			  <groupId>javax.servlet</groupId>
			  <artifactId>jstl</artifactId>
		</dependency>

		<!-- /devtools -->
		<dependency>
    		<groupId>org.springframework.boot</groupId>
    		<artifactId>spring-boot-devtools</artifactId>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.springframework.session/spring-session-core -->
		<dependency>
   			<groupId>org.springframework.session</groupId>
    		<artifactId>spring-session-core</artifactId>
		</dependency>

		<dependency>
			<groupId>com.googlecode.json-simple</groupId>
			<artifactId>json-simple</artifactId>
			<version>1.1.1</version>
		</dependency>
	</dependencies>
```

# 2.CRUD(select, insert,update ,delete)조회 추가 업데이트 패치

1. select
   컬럼 한줄을 찾을때 사용

- VO
- map

컬럼 전체조회

- List<VO>
- List<Map<String, Object>>

2. insert,update ,delete

- 보내기 int(int 형으로 보낸다(DB의 Pk가 int형이기 때문에))

insert update

- 받기 VO (VO로 컬럼한줄을 받는다 바꿀컬럼,생성할컬럼,삭제할컬럼)

delete

- 받기 int(삭제할 컬럼을 int형으로받는다 (PK가 int형이기 때문))

## 2-1. Select

```java
.xml
mybaties 찾을컬럼을 조회
id : mapper 의 메소드 이름
resultType : Select 한 데이터를 보낼 형식 (VO,map)
<select id="AllList" resultType="VO">
		SELECT
			컬럼
		FROM
			테이블
</select>

.mapper
public List<VO> AllList();

.service
public ListApartmentsVO> getSelectAllList(){
	return Mapper.AllList; //override한 mapper의 컬럼을 리턴해준다.
}

.RestController
@GetMapping("주소")
public List<VO> callAllList(){
	return service.getSelectAllList();//리스트로 리턴 제이쿼리를이용해 axjx 로받는다
}
---or
.Controller
@GetMapping("주소")
public String callAllList(ModelMap map){
	List<VO> list = service.getSelectAllList();
	map.addAttribute("명칭", list);
	return ".jsp이름";
}
.jsp
<div class="container">
	<table>
		<thead>
			<tr>
				<td>제목</td>
				<td>제목</td>
				<td>제목</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${명칭}" var="item">
				<tr>
					<td>${item.컬럼이름}</td>
					<td>${item.컬럼이름}</td>
					<td>${item.컬럼이름}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
```

## 2-2. serch(검색)

```java
.xml
<select id="selectSeach" resultType ="VO & map">
SELECT
	컬럼
	컬럼
	컬럼
FROM
	테이블
WHERE
	검색할컬럼 LIKE CONCAT(#{검색할컬럼} ,'%')//검색한컬럼의 한글자라도 있으면 조회
</select>
.mapper
public List<VO> selectSeach(String name);

.service
public List<VO> getSelectSeach(String name){
	return mapper.selectSeach(name);
}

.Controller
@GetMapping("/apartments/search")
	public String callBoardSearchPage(ModelMap map, @RequestParam("name") String name) {
		List<ApartmentsVO> list = service.getSelectSeach(name);

		map.addAttribute("List", list);

		return "apartments";
	}

.js
$('#searchBar').keyup(function(key) {
		if (key.keyCode == 13) {
			var search = $('#searchBar').val().trim();//input에 작성한 작성자를 가져옴
			if (search != '') {
				location.href="/apartments/search?search="+search//서치한 주소로 이동~
			}
		}
	});
```

## 2-3 Insert

```java
.xml
<insert id="insert">
	INSERT INTO users
		(
			컬럼이름,
			컬럼이름,
			컬럼이름
		)
	VALUES
		(
			#{VO에서 명칭한 컬럼이름},
			#{VO에서 명칭한 컬럼이름},
			#{VO에서 명칭한 컬럼이름}
		)
</insert>

.mapper
public int insert(VO vo);

.service
@Transactional(rollbackFor = Exception.class)//오류시 롤백해주는 어노테이션
public int insert(VO vo){
	return mapper.insert(vo);
}

.RestController
@CrossOrigin
@PostMapping("주소")
public int insert(@VO vo){
	return service.insert(vo);
}
//ajax json작성후 하면끝~(or post맨 주소 json작성)
--or
.Controller
@PostMapping("주소")
@ResponseBody
public int insert(@VO vo){
	return service.insert(vo);
}//ajax json작성후 하면끝~(or post맨 주소 json작성)
//등록 버튼이 있을시 & 가입input있을시.

.js
#("#인설트버튼").click(function() {
	var 컬럼1 = #("#인설트버튼").val();
	var 컬럼2 =	#("#인설트버튼").val();
	var 컬럼3 =	#("#인설트버튼").val();
	var jsonData = {
			컬럼1: 컬럼1,
			컬럼2: 컬럼2,
			컬럼3: 컬럼3
	};
	$.ajax({
			url: "(insert주소)",
			type: "POST",
			dataType: "json", //서버결과 json으로요청
			data: JSON.stringify(jsonData),//위 json타입을가져옴
			success: function(reponse) {
				if (reponse > 0) {//호출값이 0보다클때 insert
				}
			}, //success끝
		}); //ajax끝
})
```

## 2-4 update

```java
.xml
<update id="update">
UPDATE apartments
		SET
			컬럼 = #{컬럼},
			컬럼 = #{컬럼},
			컬럼 = #{컬럼},
		WHERE
			 PK컬럼 = #{VO-PK컬럼}
</update>
.mapper
public int update(VO vo);

.service
@Transactional(rollbackFor = Exception.class)//오류시 롤백해주는 어노테이션
	public int update(int pk컬럼,VO vo) {
		vo.setPk컬럼(pk컬럼);//받아온 PK번호를 set(선택)해준다
		int row =mapper.update(vo);
		return row;
	}
.RestController
@CrossOrigin
@PatchMapping("주소/{PathVariable값}")
public int update(@PathVariable("PK컬럼") int PK컬럼,
@RequestBody VO vo){
	return service.update(vo);
}

---or

.Controller
@ResponseBody//ajax로 값을보내주는 어노테이션
@PatchMapping("주소/{PathVariable값}")
public int update(@PathVariable("PK컬럼") int PK컬럼,
@RequestBody VO vo){
	return service.update(vo);
}

.js
$("#apartmentsUpdate").click(function (){
			var PK = $("#PkHidden").val();//input hidden의값을 가져온다.
			var cur1 = $('#cur1').val();
			var cur2 = $('#cur2').val();
			var cur3 = $('#cur3').val()
			var jsonData = {
					cur1 : cur1,
					cur2 : cur2,
					cur3 : cur3,
			}
			$.ajax({
				url : "/apartments/"+ PK ,
				type : 'PATCH',
				contentType: "application/json",
				dataType: "json",
				data: JSON.stringify(jsonData),
				success : function(response){
					console.log(response)
					if (response > 0) {
						if(cur1 ===''){
							 alert("데이터를 입력해주세요");
							 $('#cur1').focus()
							 return false;
						 }
						 if(cur2 ===''){
							 alert("데이터를 입력해주세요");
							 $('#cur2').focus()
							 return false;
						 }
						 if(cur3 ===''){
							 alert("데이터를 입력해주세요");
							 $('#cur3').focus()
							 return false;
						 }
						alert("수정 완료");
					}
				}
			})
	})
```

## 2-5 Delete

```java
.xml
<delete id="delete">
	DELETE FROM 테이블
	WHERE Pk = #{Pk}
</delete>

.mapper
public int delete(int Pk);

.service
@Transactional(rollbackFor = Exception.class)
public int getdelete(int Pk) {
	int row = mapper.delete(Pk);
	return row;
}

.RestContoller
//게시물 삭제(D)
@CrossOrigin
@DeleteMapping("/주소/{Pk}")
public int calldeleted(@PathVariable("Pk")int Pk) {
	return service.getdelete(Pk);
}
--- or
.controller
@ResponseBody
@DeleteMapping("/주소/{Pk}")
public int calldeleted(@PathVariable("Pk")int Pk) {
		return service.getdelete(Pk);
}
.js
//게시물 삭제
$("#contentDelete").click(function() {
	//1. 게시판 번호 확인
	var Pk = $("#PkIdHidden").val(); //hidden에 숨겨둔 Pk가져오기.
	//2. JSON생성
	var cur1 = $("#cur1").val();
	var cur2 = $("#cur2").val();
	var cur3 = $("#cur3").val();

	var jsonData = {
		cur1: cur1,
		cur2: cur2,
		cur3: cur3
	};
	//3. AJAX
	if (confirm("정말로 삭제하시겠습니까?")) {
		$.ajax({
			url: "/api/v1/board/boardId/" + boardId,
			type: "DELETE",
			contentType: "application/json", //서버에 json타입으로 요청
			dataType: "json", //서버결과 json으로요청
			data: JSON.stringify(jsonData),
			success: function(response) {
			},//success끝
		});//ajax끝
	}//config
});//삭제함수 끝
```
