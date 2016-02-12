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
            	<li><a href="/push/sendView"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>  PUSH 발송</a></li>
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
          <h2>상세 레포트</h2>
    </div>
    
    <!-- 상세 레포트 테이블 시작 -->
	<div class="col-md-8 col-md-offset-2" style="margin-bottom: 20px;">
		<div class="table-responsive">
		<label for="detailReport" style="font-size: 20px;">푸시 제목 : push test</label>
			<table id="detailReport" class="table table-hover table-bordered" style="font-size: large;">
				<tbody>
					<tr style="height: 50px;">
						<td class="active">Date</td>
						<td colspan="3">2016-02-04 14:00:00</td>
					</tr>
					<tr>
						<td class="active" style="width: 20%;">TARGET</td>
						<td></td>
						<td class="active" style="width: 20%;">OPEN</td>
						<td></td>
					</tr>
					<tr>
						<td class="active" style="width: 20%;">SENT(성공/발송)</td>
						<td></td>
						<td class="active" style="width: 20%;">NO OPEN</td>
						<td></td>
					</tr>
					<tr>
						<td class="active" style="width: 20%;">FAIL</td>
						<td></td>
						<td class="active" style="width: 20%;">CLICK</td>
						<td></td>
					</tr>
					<tr>
						<td class="active" style="width: 20%;">SMS 성공</td>
						<td></td>
						<td class="active" style="width: 20%;">SMS 실패</td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
		
		
	</div>
	<!-- 상세 레포트 테이블 끝 -->
	
</div>

</body>

</html>