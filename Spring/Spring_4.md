CREATE, DROP, ALTER (DDL)

SELECT, INSERTm DELETE, UPDATE (DML)

1. DDL과 DML 핵심 차이점? => AUTO COMMIT 여부
2. Mybatis INSERT, DELETE, UPDATE => 자동으로커밋
3. Mybitis 
4. INSERT, DELETE, UPDATE(DML, ***트랜잭션)

======================
URL주소가 같으면 x

URL 주소가 같을 떄
ex) GetMapping("/naver/news")
    POSTMapping("/naver/news")
GET, POST, DELETE, PATCH (HTTP의 메소드 가능);

- 중요한 정보를 보내거나,데이터를 많이 보낼 때 post를 사용한다.

## CRUD 작업
- C: create(insert) -Post
- R: read(select) -Get
- U: Update -Patch
- D: Delete -Delete

### @Transactional(rollbackFor = {Exception.class})
- 오류가 날시 그전 커밋으로 롤백해줌.
- Exception : 모든 오류를 잡아줌.

~~~java
//insert
	//rollbackfor: 이전 commit으로 돌아감
	//Exception : 모든에러를 잡아준다.
	@Transactional(rollbackFor = {Exception.class})
	public int setEmp(EmpVO vo) {
		int rows = EmpMapper.insertEmp(vo);//몇행 insert 되었는지 리턴
		return rows;
	}
	//delet
	@Transactional(rollbackFor = {Exception.class})
	public int getEmpRemoveCount(int empNo) {
		int rows = EmpMapper.deleteEmp(empNo);//몇행 delet 되었는지 리턴
		return rows;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public int getEmpUpdateCount(EmpVO vo) {
		int rows = EmpMapper.updateEmp(vo);//몇행 update 되었는지 리턴
		
		UserVO user = null;
		String name = user.getName();
		System.out.println(name);//UserVO값이 null이기떄문에 불어와지지 않는다. NullPointerException오류
		
		return rows;
	}
~~~

# insert / delete / update
## 1.쿼리
~~~XML
<insert id="insertEmp">
		INSERT INTO emp <-입력받을값->
		(
			empno,
			ename,
			hiredate,
			deptno,
			job,
			sal
		)
		VALUES
		(				<-입력받을값받기 ->
			#{empno},
			#{ename},
			now(),
			#{deptno},
			#{job},
			#{sal}
		)
	</insert>
	
	<delete id="deleteEmp">	<-pk값empno->
		DELETE FROM emp
		WHERE empno = #{empno}
	</delete>
	
	<update id="updateEmp">
		UPDATE emp
		SET
			comm = #{comm}
		WHERE empno = #{empno}
	</update>
~~~
## 2.Mapper
~~~java
	//insert : 한 행 에 insert하기에 파라미터값 empVO 리턴타입 int
	public int insertEmp(EmpVO empVO);
	//delete 데이터 삭제 PK로 삭제 하기떄문에 PK인 empno를 파라미터값으로 받고 int 로 리턴
	public int deleteEmp(int empno); 
	//update 한 행 에 update를 하기에 파라미터값 empVO 리턴타입 int
	public int updateEmp(EmpVO empVO);
~~~

## 3.service
~~~ java
@Transactional(rollbackFor = {Exception.class})//오류가 있으면 롤백시켜주는 함수
	public int setEmp(EmpVO vo) {
		int rows = EmpMapper.insertEmp(vo);//몇행 insert 되었는지 리턴
		return rows;
	}

	//delete
	@Transactional(rollbackFor = {Exception.class})//오류가 있으면 롤백시켜주는 함수
	public int getEmpRemoveCount(int empNo) {
		int rows = EmpMapper.deleteEmp(empNo);//몇행 delete 되었는지 리턴
		return rows;
	}
	
	@Transactional(rollbackFor = {Exception.class})//오류가 있으면 롤백시켜주는 함수
	public int getEmpUpdateCount(EmpVO vo) {
		int rows = EmpMapper.updateEmp(vo);//몇행 update 되었는지 리턴
		return rows;
	}
~~~
## 4.controller

~~~java
//emp테이블에 insert
	//@PostMapping: 중요한 정보를 보내거나, 데이터를 보낼 때 post 사용
	//대표적인 ex)회원가입
	//@RequestBody가 파라미터로 넘어오는 VO클래스를 대신 new 해줌
	@PostMapping("/emp")  //post로 받는다
	public int callEepSet(@RequestBody EmpVO empVo) {
		return empHomeService.setEmp(empVo);
	}
	
	//@DeleteMapping: 자원 삭제할 때 사용
	@DeleteMapping("/emp/empno/{empno}") //delet로 받는다
	public int callEmpRemove(@PathVariable("empno") int empno) {
		return empHomeService.getEmpRemoveCount(empno);
	}

	//update
	@PatchMapping("/emp") //patch로 받는다
	public int callEmpUpdate(@RequestBody EmpVO empVo) {
		return empHomeService.getEmpUpdateCount(empVo);
	}
~~~