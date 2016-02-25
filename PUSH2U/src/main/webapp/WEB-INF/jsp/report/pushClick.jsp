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
          <h2>Click 상세 보기</h2>
          <ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;">
          	  <li class="active">홈</li>
			  <li class="active">레포트</li>
			  <li class="active">상세 레포트</li>
			  <li class="active">Click 상세 보기</li>
		  </ol>
    </div>
    
    
    <div class="col-md-12">
          <p> 팝업 메세지 </p>
    </div>
 
    <!-- 캠페인 리스트 테이블 시작 -->
	<div class="col-md-12" style="margin-bottom: 20px;">
		<div class="table-responsive">
			<table class="table table-hover" style="font-size: large;">
				<thead>
					<tr class="active">
						<!-- <th style="width: 15%">진행 상태</th> -->
						<th style="width: 20%; text-align: center;">이미지</th>
						<th style="width: 60%; text-align: center;">URL</th>
						<th style="width: 20%; text-align: center;">클릭 수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="pList" items="${pList }">
						<tr>
							<td style="text-align: center;"><img src="${pList.IMG_URL }" style="width: 30%;"></td>
							<td style="text-align: center;">${pList.LINK }</td>
							<td style="text-align: center;">${pList.CLICK_COUNT } </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 캠페인 리스트 테이블 끝 -->
	
	 <div class="col-md-12">
          <p> 앱 내 메세지 </p>
    </div>
 
    <!-- 캠페인 리스트 테이블 시작 -->
	<div class="col-md-12" style="margin-bottom: 20px;">
		<div class="table-responsive">
			<table class="table table-hover" style="font-size: large;">
				<thead>
					<tr class="active">
						<!-- <th style="width: 15%">진행 상태</th> -->
						<th style="width: 20%; text-align: center;">이미지</th>
						<th style="width: 60%; text-align: center;">URL</th>
						<th style="width: 20%; text-align: center;">클릭 수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="mList" items="${mList }">
						<tr>
							<td style="text-align: center;"><img src="${mList.IMG_URL }"></td>
							<td style="text-align: center;">${mList.LINK }</td>
							<td style="text-align: center;">${mList.CLICK_COUNT } </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 캠페인 리스트 테이블 끝 -->
	
</div>

</body>

</html>