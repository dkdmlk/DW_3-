문제 설명
정수 num이 짝수일 경우 "Even"을 반환하고 홀수인 경우 "Odd"를 반환하는 함수(메소드), 
solution을 완성해주세요.
제한 조건
1. 정수 num을 받는 파라미터가 있어야 함.
2. return 타입은 String.
3. 메소드 이름은 solution.
입출력 예
~~~
num : 3, return : "Odd"
num : 4, return : "Even"
~~~

~~~java
public class 짝수와홀수 {
	public static String solution(int num) {
		String answer= "";						
			if(num % 2 != 0) {
				answer = "Odd"; //홀수일경우 "Odd"
			}
			if(num %2 == 0) {				
				answer = "Even";//짝수일경우 "Even"
			}					
		return answer;
	}
~~~

문제설명
전화번호가 문자열 phone_number로 주어졌을 때, 
전화번호의 뒷 4자리를 제외한 나머지 숫자를 전부 *으로 가린 문자열을 리턴하는 함수(메소드), solution을 완성해주세요.
제한조건
1. 문자형(String) phone_number을 받는 파라미터가 있어야 함.
2. return 타입은 String.
3. 메소드 이름은 solution.
4. 특정문자 추출 메소드를 사용할 것.
5. 문자길이 구하는 메소드는 length() 입니다.
~~~
    String str = "ABCDEFG";
    str.substring(0, 3); 
    /* substring(int, int) : String
    결과값 : ABC
~~~
    모든 문자 순서는 0부터 시작합니다.
    (0, 3)의 의미는 0번째 A부터 3번째 D 이전까지 문자를 자르겠다 라는 의미 입니다.
    (0, 100) 처럼 마지막 정수를 문자 길이 이상 만큼 지정하면 오류 발생. */
입출력 예
~~~
phone_number : "01033334444", return : "*******4444"
phone_number : "027778888", return : "*****8888"
~~~
~~~java
    public static String solution(String phone_number) {
		String 번호가리기 = "";
		int len = phone_number.length();
		String 번호 = "";
		String 별 = "";
		final int COUNT = 4;
		for(int i = 0;i<len;++i) {
			if(4<=len && len <=20)
			if(i < len-COUNT) {
				별 += "*";				
			}		
		}
		번호 = phone_number.substring(len-COUNT);
		번호가리기 = 별 +번호;
		return 번호가리기;
	}
~~~

문제 설명
정수를 담고 있는 배열 arr의 평균값을 return하는 함수, solution을 완성해보세요.
제한 조건
1. 정수형 타입 배열이 파라미터에 있어야 함.
2. return 타입은 double.
3. 메소드 이름은 solution.
4. int형으로 나누면 소수점이 무시됩니다.
입출력 예
~~~
arr : [1,2,3,4], return : 2.5
arr : [5,5], return : 5
~~~

~~~java
public static double solution(int[] arr) {
        double answer = 0;
        double sum = 0;
        double age = 0;
        for(int i=0;i<arr.length;++i) {
        	sum += arr[i];
        	System.out.println(sum);
        }
        age = sum / arr.length;
        answer = age;
        return answer;
    }
	
~~~

문제 설명
이 문제에는 표준 입력으로 두 개의 정수 n과 m이 주어집니다.
별(*) 문자를 이용해 가로의 길이가 n, 세로의 길이가 m인 직사각형 형태를 출력해보세요.
제한 조건
1. n과 m은 각각 1000 이하인 자연수입니다.
2. System.out.print()를 사용하면 가로로 출력됩니다.(한줄로 쭉 작성)
3. System.out.println()는 개행문자(엔터를 입력한 것 처럼).
~~~
    int x = 10;
    int y = 10;
    System.out.print(x);
    System.out.print(y);
    //결과 1010
    System.out.println(x);
    System.out.println(y);
    /* 결과
    10
    10
    */
~~~
입출력 예
~~~
a : 5,  b : 3 
*****
*****
*****
a : 2,  b : 2 
**
**
~~~
~~~java
public class 직사각형별찍기 {
	public static void main(String[] args) {
		int x = 2;
	    int y = 2;
	    if(x<=1000 && y<=1000)
	    System.out.println("x : "+ x +" ,"+ "  y : "+y);
	    for(int i = 0; i<x; ++i) {
	    	System.out.println("");
	    	for(int j=0; j<y;++j) {
	    		System.out.print("*");
	    	}
	    }
	    
	    
	}
	

}
~~~