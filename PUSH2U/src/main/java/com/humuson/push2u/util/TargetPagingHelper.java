package com.humuson.push2u.util;

/**
 * �������� ����¡ ó�� ���� ���� ����
 * @author Ȳ����
 */
public class TargetPagingHelper {
	
	// �������� ������ ���� ����
	private int rowCountPerPage;
	// �������� ������ ������ ���� 
	private int pageCountPerPage;
	// ��ȸ�� ��ü�� ����
	private int totalRowCount;
	// ����� ��û ������
	private int requestPage;
	
	
	// ��ü������ ����
	private int totalPageCount;
	// ���(�׷�) ��ȣ
	private int listNo;
	// ���� ����� ���������� ��ȣ
	private int startNoOfPreviousList;
	// ���� ����� ���������� ��ȣ
	private int startNoOfCurrentList;
	// ���� ����� ������������ ��ȣ
	private int endNoOfCurrentList;
	// ���� ����� ���������� ��ȣ 
	private int startNoOfNextList;
	
	public TargetPagingHelper(){
		this(0, 0, 0, 0);
	}
	
	public TargetPagingHelper(int rowCountPerPage, int pageCountPerPage, int totalRowCount, int requestPage) {
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
	
	/** ����¡ ó�� ��� */
	public void calculate(){
		// ��ü�������� ���
		totalPageCount = (int)Math.ceil((double)totalRowCount / rowCountPerPage);
		
		// ���� ����� ������������ȣ�� ��������������ȣ ���
		listNo = (requestPage - 1) / pageCountPerPage;
		
		//(1~5): 0, (6~10): 1, (11~15): 2, .....
		startNoOfCurrentList = (listNo * pageCountPerPage) + 1;
		endNoOfCurrentList   = (listNo * pageCountPerPage) + pageCountPerPage;
		
		// ���� ����� ���������� ��ȣ ���
		startNoOfPreviousList = startNoOfCurrentList - pageCountPerPage;
		
		if (startNoOfPreviousList < 0) { // ù��° ����� ���
			startNoOfPreviousList = 1; // 1�������� ����
		}
		
		// ���� ����� ���������� ��ȣ ���
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
	
	/** ���� ��Ͽ��� [ó������] ��� ���� ��ȯ */
	public boolean isShowFirst() {
		return listNo > 0;
	}
	
	/** ���� ��Ͽ��� [�������] ��� ���� ��ȯ */
	public boolean isShowPreviousList() {
		return listNo > 0;
	}

	/** ���� ��Ͽ��� [����������] ��� ���� ��ȯ */
	public boolean isShowPreviousPage() {
		if (requestPage > 1){
			return true;
		}
		return false;
	}
	
	/** ���� ��Ͽ��� [����������] ��� ���� ��ȯ */
	public boolean isShowNextPage() {
		if (requestPage < totalPageCount){
			return true;
		}
		return false;
	}
	
	/** ���� ��Ͽ��� [�������] ��� ���� ��ȯ */
	public boolean isShowNextList() {
		if (endNoOfCurrentList < totalPageCount){
			return true;
		}
		return false;
	}
	
	/** ���� ��Ͽ��� [������] ��� ���� ��ȯ */
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
	
	/** ����¡ ó�� ����� HTML�� ��ȯ */
	public String toHtml(String searchType, String searchValue){
		String pageListhtml = "";
		String searchQuery ="";
		
		// ��ü�˻��� �ƴѰ�� ������Ʈ�� �߰�
		if(!searchType.equals("")){
			searchQuery = "&searchType=" + searchType + "&searchValue=" + searchValue;
		}
		
		// ó������ �����ֱ� ����
		/*if(isShowFirst()){
			pageListhtml += "<span><a href='/board/list.jsp?page=1" + searchQuery + "'>ó������</a></span>";
		}*/
		
		// ������� �����ֱ� ����
		if(isShowPreviousList()){
			pageListhtml += "<li><a href='/push/pushTarget?pageNum=" + startNoOfPreviousList + "' aria-label='Previous'><span aria-hidden='true'>��</span></a></li>";
		}
		
		// ���� ������ �����ֱ� ����
		/*if(isShowPreviousPage()){
			pageListhtml += "<span><a href='?page=" + (requestPage-1) + searchQuery + "'>����������</a></span>";
		}*/
		
		//pageListhtml +=  "<span>|</span>";
		
		// ������ ��ȣ �ݺ�
		for(int i=startNoOfCurrentList; i<=endNoOfCurrentList; i++){
			if(i == requestPage){
				pageListhtml += "<li><a style='background-color : #EEEEEE;' href='/push/pushTarget?pageNum=" + i + "'>" + i + "</a></li>";
			}else{
				pageListhtml += "<li><a href='/push/pushTarget?pageNum="+i+"'>"+i+"</a></li>";
			}
		}

		// ���� ������ �����ֱ� ����
		/*if(isShowNextPage()){
			pageListhtml += "<span><a href='?page=" + (requestPage+1) + searchQuery + "'>����������</a></span>";
		}*/
		
		// ���� ��� �����ֱ� ����
		if(isShowNextList()){
			//pageListhtml += "<span><a href='?page=" + startNoOfNextList + searchQuery + "'>�������</a></span>";
			pageListhtml += "<li><a href='/push/pushTarget?pageNum=" + startNoOfNextList + "' aria-label='Previous'><span aria-hidden='true'>��</span></a></li>";;
			 
		}
		
		// ������ �����ֱ� ����
		/*if(isShowLast()){
			pageListhtml += "<span><a href='?page=" + totalPageCount + searchQuery + "'>������</a></span>";
		}*/
		return pageListhtml;
	}
	
	// �׽�Ʈ�� ���� main
	public static void main(String[] args) {
		TargetPagingHelper pagingHelper = new TargetPagingHelper(10, 5, 56, 2);
		pagingHelper.calculate();
		System.out.println("��ü�Խù���: " + pagingHelper.getTotalRowCount());
		System.out.println("����������: " + pagingHelper.getRequestPage());
		System.out.println("��ü������: " + pagingHelper.getTotalPageCount());
		System.out.println(pagingHelper.toHtml("", ""));//��ü�˻�
	}
}