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
          <h2>Target 상세 보기</h2>
          <ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;">
          	  <li class="active">홈</li>
			  <li class="active">레포트</li>
			  <li class="active">상세 레포트</li>
			  <li class="active">Target 상세 보기</li>
		  </ol>
    </div>
    
    <div class="col-md-12">
    	<div class ="col-md-6">
        	<p>타게팅 결과 : 전체 ${reportSize } 명</p>
    	</div>
    	
    	<!-- 검색 -->
	    <div class="col-md-4 col-md-offset-2">
		    <form id="reportSearchForm" action="/push/pushTarget" method="get" >
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
						<th style="width: 20%; text-align: center;">고객 ID</th>
						<th style="width: 20%; text-align: center;">Phone</th>
						<th style="width: 20%; text-align: center;">OS Ver</th>
						<th style="width: 20%; text-align: center;">Device</th>
						<th style="width: 20%; text-align: center;">App Ver</th>
					</tr>
				</thead>
				<tbody>

				<c:forEach var="report" items="${reportList }">
					<tr>
						<td style="text-align: center;">${report.CUST_ID }</td>
						<td style="text-align: center;">${report.PHONE_NUM }</td>
						<td style="text-align: center;">${report.OS_VER } </td>
						<td style="text-align: center;">${report.DEVICE }</td>
						<td style="text-align: center;">${report.APP_VER } </td>
					</tr>
				</c:forEach>
				
				<c:if test="${fn:length(reportList) == 0 }">
					<tr>
						<td colspan="5" style="text-align: center">Target 데이터가 존재하지 않습니다.</td>
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