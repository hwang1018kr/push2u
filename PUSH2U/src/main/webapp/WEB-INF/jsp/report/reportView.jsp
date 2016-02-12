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
            	<li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>PUSH 발송<span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="/push/sendView">Text Push</a></li>
		            <li><a href="/push/sendRich">Rich Push</a></li>
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
	<div class="page-header col-md-8 col-md-offset-2">
          <h2>레포트</h2>
    </div>
    
    <!-- 캠페인 리스트 테이블 시작 -->
	<div class="col-md-8 col-md-offset-2" style="margin-bottom: 20px;">
		<div class="table-responsive">
			<table class="table table-hover" style="font-size: large;">
				<thead>
					<tr class="active">
						<!-- <th style="width: 15%">진행 상태</th> -->
						<th style="width: 38%;">푸시 제목</th>
						<th style="width: 10%; text-align: center;">타겟</th>
						<th style="width: 10%; text-align: center;">성공</th>
						<th style="width: 10%; text-align: center;">오픈</th>
						<th style="width: 10%; text-align: center;">클릭</th>
						<th style="width: 12%; text-align: center;">SMS 발송</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>push test</td>
						<td style="text-align: center;">150</td>
						<td style="text-align: center;">130</td>
						<td style="text-align: center;">40</td>
						<td style="text-align: center;">5</td>
						<td style="text-align: center;">Y</td>
					</tr>
					<tr>
						<td>push test</td>
						<td style="text-align: center;">150</td>
						<td style="text-align: center;">130</td>
						<td style="text-align: center;">40</td>
						<td style="text-align: center;">5</td>
						<td style="text-align: center;">Y</td>
					</tr>
					<tr>
						<td>push test</td>
						<td style="text-align: center;">150</td>
						<td style="text-align: center;">130</td>
						<td style="text-align: center;">40</td>
						<td style="text-align: center;">5</td>
						<td style="text-align: center;">Y</td>
					</tr>
					<tr>
						<td>push test</td>
						<td style="text-align: center;">150</td>
						<td style="text-align: center;">130</td>
						<td style="text-align: center;">40</td>
						<td style="text-align: center;">5</td>
						<td style="text-align: center;">Y</td>
					</tr>
					<tr>
						<td>push test</td>
						<td style="text-align: center;">150</td>
						<td style="text-align: center;">130</td>
						<td style="text-align: center;">40</td>
						<td style="text-align: center;">5</td>
						<td style="text-align: center;">Y</td>
					</tr>
				
				<%-- <c:if test="${fn:length(reportList) == 0 }">
					<tr>
						<td colspan="5" style="text-align: center">캠페인 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if> --%>
				</tbody>
			</table>
		</div>
		
		<div class="col-md-6 col-md-offset-3">
			<nav>
				<ul id="pager" class="pager">
					
				</ul>
			</nav>
		</div>
	</div>
	<!-- 캠페인 리스트 테이블 끝 -->
	
</div>

</body>

</html>