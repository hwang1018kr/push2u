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
          <h2>Send 상세 보기</h2>
    </div>
    
    
    <div class="col-md-12">
          <p>발송성공 : 90 건</p>
    </div>
    
    
    <!-- 캠페인 리스트 테이블 시작 -->
	<div class="col-md-12" style="margin-bottom: 20px;">
		<div class="table-responsive">
			<table class="table table-hover" style="font-size: large;">
				<thead>
					<tr class="active">
						<!-- <th style="width: 15%">진행 상태</th> -->
						<th style="width: 20%; text-align: center;">User ID</th>
						<th style="width: 20%; text-align: center;">Send Date</th>
						<th style="width: 20%; text-align: center;">OS</th>
						<th style="width: 20%; text-align: center;">Device</th>
						<th style="width: 20%; text-align: center;">App Version</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="text-align: center;">sehee</td>
						<td style="text-align: center;">2016-02-04 15:17:00</td>
						<td style="text-align: center;">Android</td>
						<td style="text-align: center;">SHV-E300K</td>
						<td style="text-align: center;">1.1.2</td>
					</tr>
					<tr>
						<td style="text-align: center;">aaa</td>
						<td style="text-align: center;">2016-02-04 15:17:00</td>
						<td style="text-align: center;">Android</td>
						<td style="text-align: center;">SHV-E300K</td>
						<td style="text-align: center;">1.1.2</td>
					</tr>
					<tr>
						<td style="text-align: center;">aaa</td>
						<td style="text-align: center;">2016-02-04 15:17:00</td>
						<td style="text-align: center;">Android</td>
						<td style="text-align: center;">SHV-E300K</td>
						<td style="text-align: center;">1.1.2</td>
					</tr>
					<tr>
						<td style="text-align: center;">aaa</td>
						<td style="text-align: center;">2016-02-04 15:17:00</td>
						<td style="text-align: center;">Android</td>
						<td style="text-align: center;">SHV-E300K</td>
						<td style="text-align: center;">1.1.2</td>
					</tr>
					<tr>
						<td style="text-align: center;">aaa</td>
						<td style="text-align: center;">2016-02-04 15:17:00</td>
						<td style="text-align: center;">Android</td>
						<td style="text-align: center;">SHV-E300K</td>
						<td style="text-align: center;">1.1.2</td>
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