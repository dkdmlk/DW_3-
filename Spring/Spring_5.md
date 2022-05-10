# Controller URL을 요청받는 2가지 방법

## 1. Restful
- ex) /emp/empno/7000
>> /emp/job/manager/sal/2500
- @PathVariable: {}값을 파라미터에 대입
### Restful 규칙
1. 리소스(주소) : 동사 x -> 명사로 작성
>- ex) /member/insert(x)  /member/no/123(o)

2. 주소 이름은 대문자 x - > 소문자 o

3. 복수를 의미할 때 복수명사 사용
>- ex) /member (X) -> /members/(o)

4. 예제)핸드폰을 소유하고 있는 학생조회 (리소스 간에 연관 관계가 있는 경우)
>- 리소스명/리소스아이디/관계가있는 다른 리소스
>- ex) students/phone  
>- ex) 특정학생(1번) students/1/phone  
>- 4-1. 130번 번호를 가진 고객 주문 리스트 조회
>- 4-1-1. customeres/130/orderList
>- 4-1-2. customeres/130/order/List
>- 4-1-3. customeres/130/order-List
>- 4-2. 20번 번호를 가진 유저의 핸드폰 고유 번호는 AA123
>- 4-2-1. users/20/phone/AA123
>- 4-3. 사원번호가 7000 사원의 사수번호는 3000
>- 4-3-1. /emp/7000/mgr/3000
>- 4-3-2. /emp/empno/7000/mgr/3000

5. 마지막 주소에는 '/' 를 붙히지 않는다.
>- ex) www.naver.com/news/ (x)

## 2. QueyString
1. 검색(필터링)할 때 많이사용.
>- ex) 게시판

~~~ java
    //쿼리스트링으로 getMapping
	//tier?region=kr&name=inseok (추가로 검색할떄 & 사용)
	//검색할 때 많이 사용
	@GetMapping("/tier")
	public String calltier(@RequestParam("region") String region,@RequestParam("name") String name) {
		return region + ", " + name;
	}
~~~

1. emp 에서 없는 부서번호를 찾아서 해당 부서 번호를 insert 하기.
=> 결론 40번 찾아라! 40번 insert! @PostMapping

2. 급여가 3000이상인 사원만 삭제 @DeleteMapping

- Pk로 조회하는건 단일행