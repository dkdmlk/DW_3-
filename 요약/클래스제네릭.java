package 상속3;

import java.util.ArrayList;
import java.util.List;

class UserVO{
	private String name;
	private int sal; //급여
	
	public UserVO(String name, int sal){
		this.name = name;
		this.sal = sal;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}
}

class MainUtil{
	
	public List<UserVO> getAllUsers(List<UserVO> list){ //전체 유저 호출
		for(int i =0;i<list.size();++i) {
			System.out.println("1번 : "+list.get(i).getName()+" "+list.get(i).getSal()); 
		}
		return list;
	}
	
	public String getUser(List<UserVO> list, String name){ //특정 유저 호출
		for(int i=0; i<list.size();++i) {
			if(list.get(i).getName().equals(name)) {
				System.out.println("2번 : "+name);
			}
		}
		return name;
	}
	
	public List<UserVO> deleteUser(List<UserVO> list, int index){
		list.remove(index);
		return list;
	}
	
	public int getUserTotalSal(List<UserVO> list){
		int sum =0;
		for(int i=0; i<list.size();++i) {
			sum += list.get(i).getSal();
		}
		return sum;
	}
	
}
public class MainService extends MainUtil{
	
	public static void main(String[] args) {
		//위에 MainUtil함수를 이용해서 문제풀 것.
		//1. 전체 유저 호출
		//2. 특정 유저 호출
		//3. 특정 유저 삭제
		//4. 모든 유저 급여 합 구하기
		List<UserVO> list = new ArrayList<UserVO>();
		list.add(new UserVO("SMITH", 100));
		list.add(new UserVO("BRIAN", 300));
		list.add(new UserVO("ALLEN", 600));
		list.add(new UserVO("KING", 2000));
		MainService main = new MainService();
		main.getAllUsers(list);
		main.getUser(list, "BRIAN");
		main.getUserTotalSal(list);
		System.out.println("4번 : "+main.getUserTotalSal(list));
		main.deleteUser(list, 1);
		for(int i=0; i<list.size();++i) {
			System.out.println("3번 : "+list.get(i).getName());
		}
	}
}