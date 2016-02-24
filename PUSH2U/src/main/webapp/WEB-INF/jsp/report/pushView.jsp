<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- script -->
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/resources/js/jquery-2.2.0.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.js"></script>


<!-- css -->
<!-- <link rel="stylesheet" href="../resources/css/bootstrap.css" />
<link rel="stylesheet" href="../resources/css/bootstrap-theme.css" /> -->

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/resources/ckeditor/samples/css/samples.css">

<style type="text/css">
.table_container {
    border: 3px solid lightgrey;
    border-radius: 10px;
    position: relative;
}

.popup_button {
	bottom : 2%;
}
</style>

<script type="text/javascript">

$(function(){
//미리보기 토글 시작
	$("#toggle_status").click(function() {
		$("#push_preview_ui").hide();
		$("#inapp_preview_ui").hide();
		
		$("#status_preview_ui").show();
	});
	
	$("#toggle_push").click(function() {
		$("#status_preview_ui").hide();
		$("#inapp_preview_ui").hide();
		
		$("#push_preview_ui").show();
	});
	
	$("#toggle_inapp").click(function() {
		$("#status_preview_ui").hide();
		$("#push_preview_ui").hide();
		
		$("#inapp_preview_ui").show();
	});
})
// 미리보기 토글 끝

</script>

<title>PUSH2U - 레포트</title>
</head>
<body>


<div class="col-md-12" style="height: 80%">
	<form id="pushRichSendForm" action="sendPushRich" method="post">
		<div class="page-header col-md-12"  style="margin-top: 10px; ">
          <h2>전문 보기</h2>
          <ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;">
             <li class="active">홈</li>
		     <li class="active">레포트</li>
		     <li class="active">상세 레포트</li>
		     <li class="active">전문보기</li>
		  </ol>
    	</div>
    	
		<div class="col-md-6 col-md-offset-4 " style="margin-bottom: 20px; "id="preview">
			
			<div class="btn-group" data-toggle="buttons" style="margin-bottom: 10px;">
				<label id="toggle_status" class="btn active"> <input type="radio" name="options" id="option2" autocomplete="off"> 
					상태창
				</label> 
				<label id="toggle_push" class="btn"> <input type="radio" name="options" id="option1" autocomplete="off" checked>
					푸시
				</label> 
				<label id="toggle_inapp" class="btn"> <input type="radio"name="options" id="option3" autocomplete="off"> 
					앱 내 메시지
				</label>
			</div>
			<div id="status_preview_ui" style="display: block;">
				<div id="preview_background">
					<img src="/resources/images/android_rich_status.png">
					<div style="position:absolute; left:90px; top:220px; height:15px; width: 330px;">
						<input type="text" id="preview_title" value="${detailReport.PUSH_TITLE}" style="background-color: black; color: white; border-color: black; font-size: 12px; width: 80%; " disabled="disabled">
					</div>
					<div style="position:absolute; left:90px; top:245px; height:15px; width:330px;">
						<input type="text" id="status_preview" value="${detailReport.PUSH_MSG}" style="background-color: black; color: white; border-color: black; font-size: 12px; width: 80%; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;" disabled="disabled">
					</div>
				</div>
			</div>
			<div id="push_preview_ui" style="display: none;">
				<div id="preview_background">
					<img src="/resources/images/android_rich_popup.png">
					
					<div id="rich_popup" style="position:absolute; background-color : white; left:70px; top:200px; width: 280px; border-radius : 15px; padding: 15px; padding-bottom: 30px; word-break : break-all; min-height: 180px; max-height: 500px; overflow: auto;">${detailReport.POPUP_CONTENT}
						<button class="popup_button" style="position: absolute; background-color: gray; color: white; font-size : 13px; left:70px;  height:30px; width: 60px;" disabled="disabled">닫기</button>
						<button class="popup_button" style="position: absolute; background-color: gray; color: white; font-size : 13px; left:140px; height:30px; width: 60px;" disabled="disabled">보기</button>
					</div>
				</div>
			</div>
			<div id="inapp_preview_ui" style="display: none;">
				<div id="preview_background">
					<img src="/resources/images/android_rich_inapp.png">
					<div id="rich_inapp" style="position:absolute; left:20px; top:120px; height:600px; width: 385px; word-break : break-all; color: white; overflow: auto;">
					${detailReport.INAPP_CONTENT}
					</div>
				</div>
			</div>
		</div>

	</form>
	
</div>

</body>
</html>