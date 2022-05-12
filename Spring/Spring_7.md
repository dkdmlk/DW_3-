쿼리 결과 컬럼과 VO에 있는 변수 이름은 동일해야 함.

## SELECT : map
+ 이유: 조인이 많아지면 필드변수가 늘어난다
+ 장점: 컬럼이름이 맞게 알아서 매핑!
+ 단점: 타입을 디버깅을해봐야 안다.
## DELETE, UPDATE, INSERT :vo
+ 장점: vo클래스에 미리 필드변수를 정의해서 문서화, 유지보수 가능.

~~~XML
<-리턴타입을map으로받는경우->
<select id="selectEmpMapList" resultType="map">
		SELECT
			empno,
			ename,
			job,
			sal,
			e.deptno,
			d.dname,
			d.loc
		FROM emp as e
		Inner JOIN dept as d
		on e.deptno = d.deptno
	</select>
~~~

~~~java
Mapping
public List<Map<String, Object>> selectEmpMapList();

Service
public List<Map<String, Object>> getEmpMapList(){
		return EmpMapper.selectEmpMapList();
}

Controller
@GetMapping("emp/map/list")
	public List<Map<String, Object>> callEmpMapList(){
		return empHomeService.getEmpMapList();
}
~~~

# 리턴타입 Map
리턴타입을 Map으로 받을경우 쿼리값이 Map.put에 들어간다.

~~~ xml
<-max값찾기->
<select id="selectMapMaxsal" resultType="map">
		SELECT
			empno,
			ename,
			job,
			sal,
			e.deptno,
			d.dname,
			d.loc,
			Max(sal)
		FROM emp as e
		Inner JOIN dept as d
		on e.deptno = d.deptno
	</select>
~~~
~~~java
Mapping
public Map<String, Object> selectMapMaxsal();

Service
public Map<String, Object> getMapMaxSal(){
		List<Map<String, Object>> list=EmpMapper.selectEmpMapList();
		Map<String, Object> map = EmpMapper.selectMapMaxsal();
		for(Map<String, Object> i:list) {
			Object sal = i.get("sal");
			if(map.get("Max(sal)").equals(sal)) {//max(sal) 의 영향인지 db sal의 값의 값.00 에 의한건지 String의 값으로 비교해야 찾는값이 나옴.
				return i;
			}
		}
		return EmpMapper.selectMapMaxsal();
	}

Controller
@GetMapping("emp/map/Maxsal")
	public Map<String, Object> callMapMaxSal(){
		return empHomeService.getMapMaxSal();
}
~~~