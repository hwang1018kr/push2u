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
<script type="text/javascript" src="../resources/js/jquery.smartPop.js"></script>


<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="../resources/css/jquery.smartPop.css" />

<style type="text/css">
.table_container {
    border: 3px solid lightgrey;
    border-radius: 10px;
    position: relative;
}
</style>

<script type="text/javascript">
function popup(el){

	var url = "/push/" + el.id + "?camId=" + ${detailReport.CAM_ID };
	var name = "popup";
	window.open(url,name,"width=1000,height=500,toorbar=no,status=no,location=no,scrollbars=yes,menubar=no,resizable=yes,left=200,top=100")
}

$(document).ready(function() {

	 
//	$('#pushTarget').click(function() {
//	    $.smartPop.open({ title: '스마트팝', width: 900, height: 500, url: '/push/pushTarget?camId=${detailReport.CAM_ID}' });
//	});
	
})

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
          <h2>상세 레포트</h2>
    </div>
    
    <!-- 상세 레포트 테이블 시작 -->
	<div class="col-md-8 col-md-offset-2" style="margin-bottom: 20px;">
		<div class="table-responsive">
		
			<label for="detailReport" style="font-size: 20px;">푸시 제목 : ${detailReport.PUSH_TITLE }</label>
			<table id="detailReport" class="table table-hover table-bordered" style="font-size: large;">
				<tbody>
					<tr style="height: 50px;">
						<td class="active">Date</td>
						<td colspan="3">${detailReport.REQ_DATE }</td>
					</tr>
					<tr>
						<td class="active" style="width: 20%;">
							TARGET</br>총 발송 수
						</td>
						
						<td style="width: 30%;"> 
							${detailReport.TARGET_CNT } 건 	
							<a style="cursor:pointer" id="pushTarget" onclick="popup(this)">
								<img src="/resources/images/detail.png" style="width:20px;height:20px;" align="right" > 
							</a>
						<%--  폼전송
							<form id="pushTarget" action="pushTarget" name="pushTarget" method="post">
								<input type="hidden" class="form-control" id="camId" name="camId" value="${detailReport.CAM_ID }">	
								${detailReport.TARGET_CNT } 건 	
								<a style="cursor:pointer" onclick="document.forms['pushTarget'].submit(); return false;">
									<img src="/resources/images/detail.png" style="width:20px;height:20px;" align="right" > 
								</a>
							</form>
						--%>
						</td>
						<td class="active" style="width: 20%;">OPEN</td>
						<td> 
							${detailReport.OPEN_CNT } 건 
							<a style="cursor:pointer" id="pushOpen" onclick="popup(this)">
								<img src="/resources/images/detail.png" style="width:20px;height:20px;" align="right" > 
							</a>
						</td>
					</tr>
					<tr>
						<td class="active" style="width: 20%;">
							SENT(성공/발송)</br>성공 발송 수
						</td>
						<td> 
							${detailReport.SUCCESS_CNT } 건 / ${detailReport.TARGET_CNT } 건
							<a style="cursor:pointer" id="pushSuccess" onclick="popup(this)">
								<img src="/resources/images/detail.png" style="width:20px;height:20px;" align="right" > 
							</a>
						</td>
						<td class="active" style="width: 20%;">NO OPEN</td>
						<td> 
							${detailReport.NOOPEN_CNT } 건 
							<a style="cursor:pointer" id="pushNoOpen" onclick="popup(this)">
								<img src="/resources/images/detail.png" style="width:20px;height:20px;" align="right" > 
							</a>
						</td>
					</tr>
					<tr>
						<td class="active" style="width: 20%;">
							FAIL</br> 실패 건수
						</td>
						<td>
							${detailReport.FAIL_CNT } 건 
							<a style="cursor:pointer" id="pushFail" onclick="popup(this)">
								<img src="/resources/images/detail.png" style="width:20px;height:20px;" align="right" > 
							</a> 
						</td>
						<td class="active" style="width: 20%;">
							CLICK</br>클릭 건수
						</td>
						<td> 
							${detailReport.CLICK_CNT } 건
							<a style="cursor:pointer" id="pushClick" onclick="popup(this)">
								<img src="/resources/images/detail.png" style="width:20px;height:20px;" align="right" > 
							</a>
						</td>
					</tr>
					<c:if test="${detailReport.SMS_YN eq 'Y'}">
						<tr>
							<td class="active" style="width: 20%;">SMS 성공</td>
							<td> 
								${detailReport.SMS_SUCCESS_CNT } 건
								<a style="cursor:pointer" id="smsSuccess" onclick="popup(this)">
									<img src="/resources/images/detail.png" style="width:20px;height:20px;" align="right" >
								</a>
							</td>
							<td class="active" style="width: 20%;">SMS 실패</td>
							<td> 
								${detailReport.SMS_FAIL_CNT } 건
								<a style="cursor:pointer" id="smsFail"  onclick="popup(this)">
									<img src="/resources/images/detail.png" style="width:20px;height:20px;" align="right" > 
								</a>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>

		</div>
		
		
	</div>
	<!-- 상세 레포트 테이블 끝 -->
	<div class="modal fade bs-example-modal-sm" id="editCampModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" ></div>
	
</div>

</body>

</html>