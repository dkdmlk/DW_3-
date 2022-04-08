# 기본 사용법
~~~java
public static void main(String[] args) {
		//ArrayList<String> list//ctrl+shift+o=impot선언
		ArrayList<String> list = new ArrayList<String>();
		//add : 데이터를 list를 삽입
		//문제 : list에 저장된 곱창 파스타는 몇개
		list.add("시금치 파스타");
		list.add("곱창 파스타");
		list.add("곱창 파스타");
		list.add("곱창 파스타");
		list.add("곱창 파스타");
		list.add("곱창 파스타");
		int len = list.size();
		System.out.println("list길이는 ==>"+len);

		String value= list.get(0);
		System.out.println(value);

		value = list.get(1);
		System.out.println(value);

		int count = 0;
		for(String i : list) {
			System.out.println(i);
			if(i.equals("곱창 파스타")) {
				++count;
			}
		}
/*		for(int i=0;i<list.size();++i) {
			String value2 = list.get(i);
			if(value2.equals("곱창 파스타")) {
				++count;
			}
		}*/
		System.out.println(count);
		//int 를 담는 ArrayList
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(10);
		list2.add(40);
		list2.add(60);
		list2.add(80);
		list2.add(30);
		/*삭제
		list2.remove(1);지워지면 배열이 당겨짐*/
		int sum=0;
		int result = 0;
		for(int i : list2) {
			if(i != 40) {
				result += i;
			}
		}
		/*for(int i=0; i<list2.size();++i) {
			int value3 = list2.get(i);
			if(list2.get(i)!=40) {
				sum += value3;
			}
		}*/
		//System.out.println(sum);
		System.out.println(result);
	}
~~~

~~~java
package 컬렉션즈;

import java.util.ArrayList;

class student{
	String name;
	int age;
	String addr;
	public student(){
		
	}
	public student(String name,int age,String addr) {
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return this.age;
	}
}

public class ArrayList_Study2 {
	public static void main(String[] args) {
		student s1 = new student("홍길동",26,"부산");
		student s2 = new student("김길동",46,"부산");
		ArrayList<student> list = new ArrayList<student>();
		list.add(s1);//student 클래스 추가
		String name = list.get(0).name;//student 클래스 불러옴
		int age = list.get(0).getAge();//student 메소드(함수) 불러옴
		String addr = list.get(0).addr;
		System.out.println(name);
		System.out.println(age);
		System.out.println(addr);
		list.get(0).setAge(33);//set 메소드를불러옴
		age = list.get(0).getAge();
		System.out.println(age);
		String name2 = list.get(1).name;
		System.out.println(name2);
		int age2 = list.get(1).age;
		System.out.println(age2);
		String addr2 = list.get(1).addr;
		System.out.println(addr2);
	}
}
~~~
# Emp테이블로 예시
~~~java
public class EmpVo {
	int empno;//사원번호
	String ename;//사원이름
	String job;//직업
	String hiredate;//고용날짜
	int sal;//급여
	int deptno;//부서번호
	int comm; //성과급
	public EmpVo(int empno,String ename,String job,String hiredate,int sal,int deptno,int comm) {
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.hiredate = hiredate;
		this.sal = sal;
		this.deptno = deptno;
		this.comm = comm;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
}
~~~
~~~java
package 컬렉션즈;

import java.util.ArrayList;

public class EmpMain {
	
	//문제1메소드
	public static ArrayList<EmpVo> getEmpList(ArrayList<EmpVo> list){//EmpVo를담고있는 ArrayList를 파라미터로 받고 있다.
		for(int i=0; i<list.size();++i) {
			System.out.println("문제 1 메소드 : " +list.get(i).empno+" "+list.get(i).ename);
		}
		return list;
	}

	public static String getValue(String name) {
		System.out.println("너의 이름은?"+name);
		return name;
	}
	
	public static void main(String[] args) {
		String name = "아이유";
		String result = getValue(name);
		ArrayList<EmpVo> list = new ArrayList<EmpVo>();
		list.add(new EmpVo(7369,"SMITH","CLERK","1980-12-17",800,20,0));
		list.add(new EmpVo(7499,"ALLEN","SALESMAN","1981-02-20",1600,30,300));
		list.add(new EmpVo(7521,"WARD","SALESMAN","1981-02-22",1250,30,500));
		list.add(new EmpVo(7566,"JONES","MANAGER","1981-04-02",2975,20,0));
		list.add(new EmpVo(7654,"MARTIN","SALESMAN","1981-09-28",1250,30,1400));
		list.add(new EmpVo(7698,"BLAKE","MANAGER","1981-05-01",2850,30,0));
		list.add(new EmpVo(7782,"CLARK","MANAGER","1981-06-09",2450,10,0));
		list.add(new EmpVo(7788,"SCOTT","ANALYST","1987-06-28",3000,20,0));
		list.add(new EmpVo(7839,"KING","PRESIDENT","1981-11-17",5000,10,0));
		list.add(new EmpVo(7844,"TURNER","SALESMAN","1981-09-08",1500,30,0));
		list.add(new EmpVo(7876,"ADAMS","CLERK","1987-07-13",1100,20,0));
		list.add(new EmpVo(7900,"JAMES","CLERK","1981-12-03",950,30,0));
		list.add(new EmpVo(7902,"FORD","ANALYST","1981-12-03",3000,20,0));
		list.add(new EmpVo(7934,null,"CLERK","1982-01-23",1300,10,0));
		//일반 for
		//1. 모든 사원 번호, 이름 출력
		getEmpList(list);
		
		for(int i=0; i<list.size();++i) {
			System.out.println("문제 1 : " +list.get(i).empno+" "+list.get(i).ename);
		}
		//2. 급여가 1300달러 이상을 받는 사원의 이름,직업 조회
		for(int i=0; i<list.size();++i) {
			if(list.get(i).sal >= 1300) {
				System.out.println("문제 2. 급여가 1300이상"+list.get(i).ename+" "+list.get(i).job);
			}
		}
		//3. 직업이 SALESMAN인 사원 중 급여가 1400달러 이상 받는 사원의 번호,이름 조회
		for(int i=0; i<list.size();++i) {
			if(list.get(i).job.equals("SALESMAN")&&list.get(i).sal >= 1400) {
				System.out.println("문제 3. 직업이SALESMAN 급여 1400이상 : "+list.get(i).empno+" "+list.get(i).ename);
			}
		}
		//4. 입사년도가 1981년도인 사원의 번호,이름 조회
		for(int i=0; i<list.size();++i) {
			String[] hiredate = list.get(i).hiredate.split("-");
			if(hiredate[0].equals("1981")) {
				System.out.println("문제 4. 입사년도가 1981인 회원 : "+list.get(i).ename+" "+list.get(i).empno);
			}
		}
		//5. 직업이 MANAGER인 사원 수 조회
		int count = 0;
		for(int i=0; i<list.size();++i) {
			if(list.get(i).job.equals("MANAGER")) {
				++count;
			}
		}
		System.out.println("문제 5. 직업이 MANAGER인 사원 수 "+count+"명");
		//6. 사원 중 급여를 가장 많이 받는 사원의 번호,이름,입사년도 조회
		int max = 0;
		String maxSal= "";
		for(int i=0; i<list.size();++i) {
			if(list.get(i).sal > max) {
				max = list.get(i).sal;
			}
			if(max == list.get(i).sal) {
				maxSal = "문제 6 ."+list.get(i).empno+" "+list.get(i).ename+" "+list.get(i).hiredate;
			}
		}
		System.out.println(maxSal);
		//7. 부서번호별 수 조회 ex) 20 : 2명, 30: 3명
		int cnt =0;
		int cnt2 =0;
		for(int i=0; i<list.size();++i) {
			if(list.get(i).deptno ==20) {
				++cnt;
			}
			if(list.get(i).deptno ==30) {
				++cnt2;
			}
		}
		System.out.println("문제 7. deptno 20 : "+cnt+"명 "+"deptno 30 : "+cnt2+"명");
		//8. 입사월이 02월인 사원 수 조회
		int count2 = 0;
		for(int i=0; i<list.size();++i) {
			String[] hiredate = list.get(i).hiredate.split("-");
			if(hiredate[1].equals("02")) {
				++count2;
			}
		}
		System.out.println("문제 8. "+ count2+"명");
		//1. comm을 받은 사원 수와 직업,이름을 조회하시오.
		for(int i=0; i<list.size();++i) {
			if(list.get(i).comm > 0) {
				System.out.println("문제B-1. "+list.get(i).ename+" "+list.get(i).job);
			}
		}
		//2. emp에 있는 급여 평균을 구하시오.
		int sum =0;
		int age = 0;
		for(int i=0; i<list.size();++i) {
			sum += list.get(i).sal;
			age = sum / list.size();
		}
		System.out.println("문제B-2. "+age);
		//3. 사원번호가 7844, 7876인 사원에게 comm 200을 지급하시오.
		for(int i=0; i<list.size();++i) {
			if(list.get(i).empno == 7844 || list.get(i).empno == 7876) {
				list.get(i).comm = 200;
				System.out.println("문제B-3. "+list.get(i).empno+" "+list.get(i).comm);
			}
		}
		//4. 사원이름이 null인 사원이름에 '데이터 없음' 으로 수정하시오. 
		for(int i=0; i<list.size();++i) {
			if(list.get(i).ename == null) {
				list.get(i).ename = "데이터 없음";
				System.out.println("문제B-4. "+list.get(i).ename+" "+list.get(i).empno);
			}
		}
		//5. 20번 부서가 올해 실적이 좋지 않습니다. 20번부서를 모두 해고(제거) 하십시오.
		for(int i=0; i<list.size();++i) {
			if(list.get(i).deptno == 20) {
				System.out.println("문제B-5. 삭제번호 "+list.get(i).deptno+" "+list.get(i).empno);
				list.remove(list.get(i));
			}
		}
		for(int i=0; i<list.size();++i) {
			System.out.println("문제B-5. 남은번호 "+list.get(i).deptno+" "+list.get(i).empno+" "+list.get(i).ename);
		}
		
//		//for-each
//		for(EmpVo vo: list) {
//			
//		}
	}

}
~~~