# Mybatis if

~~~XML
<!--	
    true 가 파라미터로 들어오면 사수가 있는 사원조회
	flase가 파라미터로 들어오면 사수 없는 사원 조회 
	Mybatis는 boolean형이 없기떄문에 문자로 비교해야함.
	-->
	<select id="selectEmpMgr" resultType="EmpVO">
		SELECT
			empno,
			ename,
			mgr
		FROM 
			emp
		WHERE 1=1
		<if test='isMgr.equals("true")'>
			AND	mgr IS NOT NULL
		</if>
		<if test='isMgr.equals("false")'>
			AND	mgr IS NULL
		</if>
~~~
~~~JAVA
Mapper
public List<EmpVO> selectEmpMgr(@Param("isMgr") String isMgr);

Service
public List<EmpVO> getEmpIsMgrList(String isMgr){
		return EmpMapper.selectEmpMgr(isMgr);
}

Controller
//사원사수여부
	@GetMapping("/emp/mgr/{isMgr}")
	public List<EmpVO> callEmpIsMgrList(@PathVariable("isMgr") String isMgr){
		return empHomeService.getEmpIsMgrList(isMgr);
	}
~~~
## if문 true false 여부
Postman 으로 http://localhost:8080/emp/mgr/(true or false)결정

~~~ xml
<-문제1. 사원번호가 7902번인 사원
job을 SALESMAN, Sal을 3500으로 수정->
<update id="updateJobSal">
		UPDATE emp
			SET
			job = #{job},
			sal = #{sal}
		WHERE empno = #{empno}
	</update>
~~~
~~~java
Mapping
public int  updateJobSal(EmpVO empVO);

Service
public int getUpdateSalJob(int empno, EmpVO vo) {
		vo.setEmpno(empno);
		return EmpMapper.updateJobSal(vo);
}

Controller
@PatchMapping("/emp/{empno}")
	public int callEmpSalJobUpdate(@PathVariable("empno")int empno, @RequestBody EmpVO empvo) {
		return empHomeService.getUpdateSalJob(empno, empvo);
	}
~~~

~~~ xml
<-문제2. 사원번호가 7844번인 사원의 
		comm이 0이거나 null이면 기존 급여에서 500을 추가 (수정)하시오
		comm 이 있다며 0을 리턴->
<select id="selectEmpCommSal" resultType="EmpVO" >
		select 
			empno,
			comm,
			sal
		from
			emp
		where 
			empno = 7844
	</select>
	
	<update id="updateEmpsal">
		UPDATE emp
		SET
			sal = #{sal}
		WHERE empno = #{empno}
	</update>
~~~
~~~java
Mapping
public EmpVO selectEmpCommSal(@Param("empno")int empno); //찾는로직
public int updateEmpsal(EmpVO vo);//업데이트해주는 로직

Service
@Transactional(rollbackFor = Exception.class)
public int getEmpUpdateSalCount(int empno) {
	EmpVO vo = EmpMapper.selectEmpCommSal(empno);
	int comm = vo.getComm();
	if(comm == 0) {
		int bouns = 500;
		int sal = vo.getSal();
		vo.setSal(sal + bouns);
		//Update 로직 추가
		return EmpMapper.updateEmpsal(vo);
	}
	return 0;
}
@PatchMapping("/emp/empno/{empno}")
public int callEmpCommUpdate(@PathVariable("empno")int empno) {
	return empHomeService.getEmpUpdateSalCount(empno);
}//postman row 로 받을려면-(@RequestBody EmpVO empvo) 파라미터값추가
/*
@PatchMapping("/emp/empno/{empno}")
public int callEmpCommUpdate(@PathVariable("empno")int empno, @RequestBody EmpVO empvo) {
	return empHomeService.getEmpUpdateSalCount(empno);
}
*/
~~~