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

$("#btnSearch").click(function(){
	 $("#reportSearchForm").submit();
});

</script>

<title>PUSH2U - 레포트</title>
</head>
<body>



<div class="col-md-12" style="height: 30%">
	<div class="page-header col-md-12">
          <h2>Fail 상세 보기</h2>
          <ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;">
          	  <li class="active">홈</li>
			  <li class="active">레포트</li>
			  <li class="active">상세 레포트</li>
			  <li class="active">Fail 상세 보기</li>
		  </ol>
    </div>
    
    
    <div class="col-md-12">
    	<div class ="col-md-6">
        	<p>발송실패 : ${reportSize } 건</p>
    	</div>
    
    	<!-- 검색 -->
	    <div class="col-md-4 col-md-offset-2">
		    <form id="reportSearchForm" action="/push/pushFail" method="get" >
			    <div class="input-group">
			    	<input type="hidden" name="camId" class="form-control" value="${camId }" >
			    	<input type="text" name="searchValue" class="form-control" placeholder="고객 ID ..." >
			    		<span class="input-group-btn">
			    			<button id="btnSearch" class="btn btn-default" type="submit">Search</button>
			      		</span>
			    </div><!-- /input-group -->
		    </form>
		</div><!-- /.col-lg-6 -->
    
    </div>
    
    
    <!-- 캠페인 리스트 테이블 시작 -->
	<div class="col-md-12" style="margin-bottom: 20px;">
		<div class="table-responsive">
			<table class="table table-hover" style="font-size: large;">
				<thead>
					<tr class="active">
						<!-- <th style="width: 15%">진행 상태</th> -->
						<th style="width: 15%; text-align: center;">고객 ID</th>
						<th style="width: 20%; text-align: center;">Send Date</th>
						<th style="width: 10%; text-align: center;">OS Ver</th>
						<th style="width: 15%; text-align: center;">Device</th>
						<th style="width: 15%; text-align: center;">App Ver</th>
						<th style="width: 25%; text-align: center;">Fail Description</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach var="report" items="${reportList }"> 
						<tr>
							<td style="text-align: center;">${report.CUST_ID }</td>
							<td style="text-align: center;">${report.RES_DATE }</td>
							<td style="text-align: center;">${report.OS_VER }</td>
							<td style="text-align: center;">${report.DEVICE }</td>
							<td style="text-align: center;">${report.APP_VER }</td>
							<td style="text-align: center;">
								<c:choose>
									<c:when test="${report.RES_CD == 0000 }">토큰 변경 완료</c:when>
									<c:when test="${report.RES_CD == 3000 }">전송중</c:when>
									<c:when test="${report.RES_CD == 3203 }">에티켓 타임</c:when>
									<c:when test="${report.RES_CD == 4000 }">UNKNOWN FAIL</c:when>
									<c:when test="${report.RES_CD == 4100 }">메시지 거부</c:when>
									<c:when test="${report.RES_CD == 4400 }">푸시 거부</c:when>
									<c:when test="${report.RES_CD == 4401 }">DENY_MKT_PUSH</c:when>
									<c:when test="${report.RES_CD == 4500 }">DENY_APP_VERSION</c:when>
									<c:when test="${report.RES_CD == 5000 }">미등록 고객ID</c:when>
									<c:when test="${report.RES_CD == 6000 }">잘못된 파라미터</c:when>
									<c:when test="${report.RES_CD == 7000 }">PRIVATE_FEEDBACK_TIMEOUT</c:when>
									<c:when test="${report.RES_CD == 7500 }">TTL_EXPIRED</c:when>
									<c:when test="${report.RES_CD == 8000 }">NO_SEND</c:when>
									<c:when test="${report.RES_CD == 8500 }">PRIVATE_SERVER_ERROR</c:when>
									<c:when test="${report.RES_CD == 9000 }">TODAY_SEND_FILTER</c:when>
									<c:when test="${report.RES_CD == 9999 }">PENDING</c:when>
									<c:otherwise>기타 오류</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				
				<c:if test="${fn:length(reportList) == 0 }">
					<tr>
						<td colspan="6" style="text-align: center">Fail 데이터가 존재하지 않습니다.</td>
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