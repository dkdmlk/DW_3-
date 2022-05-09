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

CRUD
- C: create(insert)
- R: read(select)
- U: Update
- D: Delete

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