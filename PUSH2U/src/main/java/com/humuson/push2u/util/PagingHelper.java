package com.humuson.push2u.util;

/**
 * 웹페이지 페이징 처리 관련 정보 제공
 * @author 황집중
 */
public class PagingHelper {
	
	// 페이지당 보여질 행의 갯수
	private int rowCountPerPage;
	// 페이지당 보여질 페이지 개수 
	private int pageCountPerPage;
	// 조회된 전체행 갯수
	private int totalRowCount;
	// 사용자 요청 페이지
	private int requestPage;
	
	
	// 전체페이지 갯수
	private int totalPageCount;
	// 목록(그룹) 번호
	private int listNo;
	// 이전 목록의 시작페이지 번호
	private int startNoOfPreviousList;
	// 현재 목록의 시작페이지 번호
	private int startNoOfCurrentList;
	// 현재 목록의 마지막페이지 번호
	private int endNoOfCurrentList;
	// 다음 목록의 시작페이지 번호 
	private int startNoOfNextList;
	
	public PagingHelper(){
		this(0, 0, 0, 0);
	}
	
	public PagingHelper(int rowCountPerPage, int pageCountPerPage, int totalRowCount, int requestPage) {
		this.rowCountPerPage = rowCountPerPage;
		this.pageCountPerPage = pageCountPerPage;
		this.totalRowCount = totalRowCount;
		this.requestPage = requestPage;
	}

	public int getRowCountPerPage() {
		return rowCountPerPage;
	}

	public void setRowCountPerPage(int rowCountPerPage) {
		this.rowCountPerPage = rowCountPerPage;
	}

	public int getPageCountPerPage() {
		return pageCountPerPage;
	}

	public void setPageCountPerPage(int pageCountPerPage) {
		this.pageCountPerPage = pageCountPerPage;
	}

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public int getRequestPage() {
		return requestPage;
	}

	public void setRequestPage(int requestPage) {
		this.requestPage = requestPage;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
	public int getListNo() {
		return listNo;
	}

	public void setListNo(int listNo) {
		this.listNo = listNo;
	}

	public int getStartNoOfCurrentList() {
		return startNoOfCurrentList;
	}

	public void setStartNoOfCurrentList(int startNoOfCurrentList) {
		this.startNoOfCurrentList = startNoOfCurrentList;
	}

	public int getEndNoOfCurrentList() {
		return endNoOfCurrentList;
	}

	public void setEndNoOfCurrentList(int endNoOfCurrentList) {
		this.endNoOfCurrentList = endNoOfCurrentList;
	}

	public int getStartNoOfPreviousList() {
		return startNoOfPreviousList;
	}

	public void setStartNoOfPreviousList(int startNoOfPreviousList) {
		this.startNoOfPreviousList = startNoOfPreviousList;
	}

	public int getStartNoOfNextList() {
		return startNoOfNextList;
	}

	public void setStartNoOfNextList(int startNoOfNextList) {
		this.startNoOfNextList = startNoOfNextList;
	}
	
	/** 페이징 처리 계산 */
	public void calculate(){
		// 전체페이지수 계산
		totalPageCount = (int)Math.ceil((double)totalRowCount / rowCountPerPage);
		
		// 현재 목록의 시작페이지번호와 마지막페이지번호 계산
		listNo = (requestPage - 1) / pageCountPerPage;
		
		//(1~5): 0, (6~10): 1, (11~15): 2, .....
		startNoOfCurrentList = (listNo * pageCountPerPage) + 1;
		endNoOfCurrentList   = (listNo * pageCountPerPage) + pageCountPerPage;
		
		// 이전 목록의 시작페이지 번호 계산
		startNoOfPreviousList = startNoOfCurrentList - pageCountPerPage;
		
		if (startNoOfPreviousList < 0) { // 첫번째 목록인 경우
			startNoOfPreviousList = 1; // 1페이지로 설정
		}
		
		// 다음 목록의 시작페이지 번호 계산
		startNoOfNextList = startNoOfCurrentList + pageCountPerPage;
		
		
		if (startNoOfCurrentList > totalPageCount){
			if(totalPageCount != 0) {
				startNoOfCurrentList = startNoOfCurrentList - pageCountPerPage + 1;
			}
		}
		if (endNoOfCurrentList > totalPageCount){
			endNoOfCurrentList = totalPageCount;
		}
	}
	
	/** 현재 목록에서 [처음으로] 출력 여부 반환 */
	public boolean isShowFirst() {
		return listNo > 0;
	}
	
	/** 현재 목록에서 [이전목록] 출력 여부 반환 */
	public boolean isShowPreviousList() {
		return listNo > 0;
	}

	/** 현재 목록에서 [이전페이지] 출력 여부 반환 */
	public boolean isShowPreviousPage() {
		if (requestPage > 1){
			return true;
		}
		return false;
	}
	
	/** 현재 목록에서 [다음페이지] 출력 여부 반환 */
	public boolean isShowNextPage() {
		if (requestPage < totalPageCount){
			return true;
		}
		return false;
	}
	
	/** 현재 목록에서 [다음목록] 출력 여부 반환 */
	public boolean isShowNextList() {
		if (endNoOfCurrentList < totalPageCount){
			return true;
		}
		return false;
	}
	
	/** 현재 목록에서 [끝으로] 출력 여부 반환 */
	public boolean isShowLast() {
		if (endNoOfCurrentList < totalPageCount){
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Page [rowCountPerPage=" + rowCountPerPage
				+ ", pageCountPerPage=" + pageCountPerPage + ", totalRowCount="
				+ totalRowCount + ", requestPage=" + requestPage
				+ ", totalPageCount=" + totalPageCount + ", listNo=" + listNo
				+ ", startNoOfCurrentList=" + startNoOfCurrentList
				+ ", endNoOfCurrentList=" + endNoOfCurrentList
				+ ", startNoOfPreviousList=" + startNoOfPreviousList
				+ ", startNoOfNextList=" + startNoOfNextList + "]";
	}
	
	/** 페이징 처리 목록을 HTML로 반환 */
	public String toHtml(String searchType, String searchValue, String reportString, int camId){
		String pageListhtml = "";
		String searchQuery = "";
		String camIdString = "";
		
		if (!reportString.equals("reportView")){
			camIdString = "&camId=" + camId;
		}
		
		// 전체검색이 아닌경우 쿼리스트링 추가
		if(!searchType.equals("")){
			searchQuery = "&searchType=" + searchType + "&searchValue=" + searchValue;
		}
		
		
		// 이전목록 보여주기 여부
		if(isShowPreviousList()){
			pageListhtml += "<li><a href='/push/" + reportString + "?pageNum=" + startNoOfPreviousList + camIdString + "' aria-label='Previous'><span aria-hidden='true'>《</span></a></li>";
		}
		

		// 페이지 번호 반복
		for(int i=startNoOfCurrentList; i<=endNoOfCurrentList; i++){
			if(i == requestPage){
				pageListhtml += "<li><a style='background-color : #EEEEEE;' href='/push/" + reportString + "?pageNum=" + i + camIdString + "'>" + i + "</a></li>";
			}else{
				pageListhtml += "<li><a href='/push/" + reportString + "?pageNum=" + i + camIdString + "'>" + i + "</a></li>";
			}
		}

		// 다음 목록 보여주기 여부
		if(isShowNextList()){
			//pageListhtml += "<span><a href='?page=" + startNoOfNextList + searchQuery + "'>다음목록</a></span>";
			pageListhtml += "<li><a href='/push/" + reportString + "?pageNum=" + startNoOfNextList + camIdString + "' aria-label='Previous'><span aria-hidden='true'>》</span></a></li>";;
			 
		}
		
		return pageListhtml;
	}
	
	// 테스트을 위한 main
	public static void main(String[] args) {
		PagingHelper pagingHelper = new PagingHelper(10, 5, 56, 2);
		pagingHelper.calculate();
		System.out.println("전체게시물수: " + pagingHelper.getTotalRowCount());
		System.out.println("현재페이지: " + pagingHelper.getRequestPage());
		System.out.println("전체페이지: " + pagingHelper.getTotalPageCount());
		//System.out.println(pagingHelper.toHtml("", ""));//전체검색
	}
}
