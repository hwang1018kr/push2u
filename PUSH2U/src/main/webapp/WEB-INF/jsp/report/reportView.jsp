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
<meta name="viewport" content="width=device-width, initial-scale=1">

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

<!-- top menu 시작 -->
<div class="col-md-12" style="height: 10%">
	<nav class="navbar navbar-default navbar-fixed-top">
    	<!-- We use the fluid option here to avoid overriding the fixed width of a normal container within the narrow content columns. -->
    	<div class="container-fluid">
        	<div class="navbar-header">
          		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-6">
	            	<span class="sr-only">Toggle navigation</span>
	            	<span class="icon-bar"></span>
	            	<span class="icon-bar"></span>
	            	<span class="icon-bar"></span>
          		</button>
        		<span id="brand" class="navbar-brand " >PUSH2U</span>
        	</div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-6">
        	<ul class="nav navbar-nav">
            	
            	<li><a href="/push/home"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>  홈</a></li>
            	<li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>  PUSH 발송<span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="/push/sendView"><span class="glyphicon glyphicon-text-size" aria-hidden="true"></span>  Text Push</a></li>
		            <li><a href="/push/sendRich"><span class="glyphicon glyphicon-header" aria-hidden="true"></span>  Rich Push</a></li>
		          </ul>
		        </li>
            	<li><a href="/push/reportView"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>  레포트</a></li>
        	</ul>
        	<ul class="nav navbar-nav navbar-right">
          		<li><p class="navbar-text"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>  ${sessionScope.userLoginInfo.userId } 님</p></li>
            	<li class="bg-danger"><a href="/user/logout"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> 로그아웃</a></li>
        	</ul>
      	</div><!-- /.navbar-collapse -->
      </div>
    </nav>
</div>
<!-- top menu 끝 -->

<div class="col-md-12" style="height: 30%">
	<div class="page-header col-md-10 col-md-offset-1">
          <h2>레포트</h2>
          <ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;">
          	  <li class="active">홈</li>
			  <li class="active">레포트</li>
		  </ol>
    </div>
    
    <!-- 검색 -->
    <div class="col-md-2 col-md-offset-1" style="margin-bottom: 20px;">
	    <form id="reportSearchForm" action="/push/reportView" method="get" >
		    <div class="input-group">
		    	<input type="text" name="searchValue" class="form-control" placeholder="푸시 제목" >
		    		<span class="input-group-btn">
		    			<button id="btnSearch" class="btn btn-info" type="submit"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>  검색</button>
		      		</span>
		    </div><!-- /input-group -->
	    </form>
	</div><!-- /.col-lg-6 -->
    
    <!-- 캠페인 리스트 테이블 시작 -->
	<div class="col-md-10 col-md-offset-1" style="margin-bottom: 20px;">
		<div class="table-responsive">
			<table class="table table-hover" style="font-size: large;">
				<thead>
					<tr class="active">
						<!-- <th style="width: 15%">진행 상태</th> -->
						<th style="width: 5%;text-align: center;">타입</th>
						<th style="width: 35%;">푸시 제목</th>
						<th style="width: 20%;text-align: center;">발송 날짜</th>
						<th style="width: 7%; text-align: center;">타겟</th>
						<th style="width: 7%; text-align: center;">성공</th>
						<th style="width: 7%; text-align: center;">오픈</th>
						<th style="width: 7%; text-align: center;">클릭</th>
						<th style="width: 12%; text-align: center;">SMS 발송</th>
					</tr>
				</thead>
				<tbody>
				
				<c:forEach var="report" items="${reportList }">
					<tr>
						<td style="text-align: center;"> 
							<c:choose>
							    <c:when test="${report.MSG_TYPE eq 'H'}">
							        <img src="/resources/images/rich.png" style="width:37px;height:22px;" align="middle" >
							    </c:when>
						
							    <c:otherwise>
							       <img src="/resources/images/text.png" style="width:37px;height:22px;" align="middle" >
							    </c:otherwise>
							</c:choose>
						</td>
						<td style="white-space: nowrap;"><a href="/push/detailReport?camId=${report.CAM_ID }&pageNum=${pageNum }" id="detailReport" class="detailReportBtn">${report.PUSH_TITLE }</a></td>
						<td style="text-align: center;">${report.REQ_DATE }</td>
						<td style="text-align: center;">${report.TARGET_CNT }</td>
						<td style="text-align: center;">${report.SUCCESS_CNT }</td>
						<td style="text-align: center;">${report.OPEN_CNT } </td>
						<td style="text-align: center;">${report.CLICK_CNT }</td>
						<td style="text-align: center;">${report.SMS_YN } </td>
					</tr>
				</c:forEach>
				
				<c:if test="${fn:length(reportList) == 0 }">
					<tr>
						<td colspan="8" style="text-align: center">캠페인 데이터가 존재하지 않습니다.</td>
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