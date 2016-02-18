<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!-- script -->
<script type="text/javascript" src="../resources/js/jquery-2.2.0.js"></script>
<script type="text/javascript" src="../resources/js/bootstrap.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">


<style type="text/css">
.table_container {
    border: 3px solid lightgrey;
    border-radius: 10px;
    position: relative;
}
</style>

<script type="text/javascript">


</script>

<title>PUSH2U - 레포트</title>
</head>
<body>



<div class="col-md-12" style="height: 30%">
	<div class="page-header col-md-12">
          <h2>SMS 실패 상세 보기</h2>
          <ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;">
			  <li class="active">레포트</li>
			  <li class="active">상세 레포트</li>
			  <li class="active">SMS 실패 상세 보기</li>
		  </ol>
    </div>
    
    
    <div class="col-md-12">
          <p>발송실패 : ${reportSize } 건</p>
    </div>
    
    
    <!-- 캠페인 리스트 테이블 시작 -->
	<div class="col-md-12" style="margin-bottom: 20px;">
		<div class="table-responsive">
			<table class="table table-hover" style="font-size: large;">
				<thead>
					<tr class="active">
						<!-- <th style="width: 15%">진행 상태</th> -->
						<th style="width: 20%; text-align: center;">User ID</th>
						<th style="width: 20%; text-align: center;">Phone</th>
						<th style="width: 20%; text-align: center;">Send Date</th>
						<th style="width: 40%; text-align: center;">Fail Description</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="report" items="${reportList }">
						<tr>
							<td style="text-align: center;">${report.CUST_ID }</td>
							<td style="text-align: center;">${report.SEND_TO }</td>
							<td style="text-align: center;">${report.REQ_DATE } </td>
							<td style="text-align: center;">
								<c:choose>
									<c:when test="${report.RSLT_CODE2 eq '1' }">TIMEOUT</c:when>
									<c:when test="${report.RSLT_CODE2 eq 'C' }">전원 꺼짐</c:when>
									<c:when test="${report.RSLT_CODE2 eq '2' }">잘못된 전화번호</c:when>
									<c:when test="${report.RSLT_CODE2 eq 'Z' }">기타 실패</c:when>
									<c:when test="${report.RSLT_CODE2 eq 'k' }">이통사 스팸 처리</c:when>
									<c:when test="${report.RSLT_CODE2 eq 'o' }">메시지 길이 초과</c:when>
									<c:when test="${report.RSLT_CODE2 eq 'p' }">번호 형식 오류</c:when>
									<c:when test="${report.RSLT_CODE2 eq 'Q' }">필드 형식 오류</c:when>
									<c:when test="${report.RSLT_CODE2 eq 'q' }">메시지 중복키 체크</c:when>
									<c:when test="${report.RSLT_CODE2 eq 'y' }">하루 메시지 수량 초과</c:when>
									<c:otherwise>기타 오류</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				
					<c:if test="${fn:length(reportList) == 0 }">
						<tr>
							<td colspan="5" style="text-align: center">캠페인 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<div class="col-md-6 col-md-offset-3">
			<nav>
				<ul id="pager" class="pager">
					${pagerHtml }
				</ul>
			</nav>
		</div>
	</div>
	<!-- 캠페인 리스트 테이블 끝 -->
	
</div>

</body>

</html>