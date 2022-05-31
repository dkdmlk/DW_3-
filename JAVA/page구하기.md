~~~java
package 페이징;

public class PageHandler {
	
	public static void main(String[] args) {
		//여신강림 페이징
		//야옹이 작가는 210화 싸지연재함.
		//step 1.페이지수 알아내기(총카운트와 한페이지에 몇개를 보여줄지가 중요)
		int total = 219; //sql 에서 count
		//네이버 웹툰기준으로 1페이지는 10개에 게시물을 보여줄수 있다.
		int page = 0;
		int pageSize = 10;
		//야옹이작가는 219화 까지 연재함
		page = total/pageSize;
		if(total % pageSize > 0) {
			++page;
		}
		
		System.out.println("총페이지수 =====>" + page);
		//step 2. 블록 알아내기
		int navigatePages = 10;//한블록에 몇페이지를 보여줄지
		int blocks = 0;
		
		blocks = (page / navigatePages);
		if(page % navigatePages > 0) {
			++blocks;
		}
		System.out.println("블록수 ==>"+blocks);
		//step3. 현재 블록 알아내기
		int pageNum = 1; //현재페이지
		int nowBlock = 0;
		int nowpage = 0;
		nowpage = page / pageNum;
		//현재페이지
		
		nowBlock = pageNum / pageSize;
		if(pageNum % pageSize > 0) {
			++nowBlock;
		}
		
		System.out.println("현재블록은 ===>" + nowBlock);
		
		//stap4. 마지막블록알아내기
		int lastBlock = 0;
		lastBlock = (total/navigatePages)/pageSize;
		if(total%navigatePages%pageSize > 0) {
			++lastBlock;
		}
		System.out.println("마지막블록은 ===>" + lastBlock);
		
		//step5. 현재 내가 위치한 블록의 처음 페이지와 마지막페이지 구하기
		/*ex 1블록에 있으면 1~10
		 * 	 2블록에 있으면 11~20
		 * 	 3블록에 있으면 21~22
		 * */
		int startPage = 0;
		int endPage = 0;
		startPage = (nowBlock*navigatePages) - (navigatePages-1);
		endPage = nowBlock*navigatePages;
		if(nowBlock == lastBlock) {
			endPage = page;
		}
		System.out.println("처음 페이지 ===>" + startPage);
		System.out.println("마지막페이지 ===>" + endPage);
		
		//step6. 이전버튼 , 다음버특 유무 판단
		boolean hasPreviousPage = true;//이전 버튼
		boolean hasNextPage = true;//다음 버튼
		if(lastBlock == 1) {//블록이 하나이므로 이전,다음 버튼 존재x
			hasPreviousPage = false;
			hasNextPage = false;
		}
		if(lastBlock > 1 && lastBlock == nowBlock) {//마지막 블록이라면 이전 버튼만 존재
			hasPreviousPage = true;
			hasNextPage = false;
		}
		if(lastBlock > 1 && pageNum <= navigatePages) {// 첫번째블록인데 블록이 1이상일 경우 다음버튼 존재
			hasPreviousPage = false;
			hasNextPage = true;
		}

		
		System.out.println("이전버튼이 있습니까?" + hasPreviousPage);
		System.out.println("다음버튼이 있습니까?" + hasNextPage);
		
		
		
	}
}


~~~