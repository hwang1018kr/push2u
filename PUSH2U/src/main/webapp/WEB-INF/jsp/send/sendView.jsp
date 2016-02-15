<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- script -->
<script type="text/javascript" src="/resources/js/jquery-2.2.0.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.js"></script>

<!-- css -->
<!-- <link rel="stylesheet" href="../resources/css/bootstrap.css" />
<link rel="stylesheet" href="../resources/css/bootstrap-theme.css" /> -->

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
$(function () {
	
	// 미리보기 토글 시작
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
	// 미리보기 토글 끝
	
	// 푸시 타이틀 미리보기 입력
	$("#push_title").keyup(function() {
		
		limitByte(this, 40);
		
		var title = $("#push_title").val();
		
		$("#preview_title").val("");
		$("#preview_title").val(title);
		
	});
	
	// 푸시 팝업 내용 미리보기 입력
	$("#popup_contents").keyup(function() {
		
		limitByte(this, 80);
		
		var popup_contents = $("#popup_contents").val();
		
		$("#popup_preview").val("");
		$("#popup_preview").val(popup_contents);
		
		$("#push_preview").val("");
		$("#push_preview").val(popup_contents);
		
	});
	
	// 앱 내 메시지 미리보기 입력
	$("#inapp_contents").keyup(function() {
		
		limitByte(this, 3500);
		
		var inapp_contents = $("#inapp_contents").val();
		
		$("#inapp_preview").val("");
		$("#inapp_preview").val(inapp_contents);
		
	});
	
	// 바이트 제한 함수
	function limitByte(obj, bytes){
        var text = $(obj).val();
        var leng = text.length;
        while(getTextLength(text) > bytes) {
        	
            leng--;
            text = text.substring(0, leng);
            
        }
        $(obj).val(text);
        
        if (bytes == 80) {
        	$('#popup_byteInfo').text(getTextLength(text));
        } else if (bytes == 3500) {
        	$('#inaap_byteInfo').text(getTextLength(text));
        }
        
    }
	
	function getTextLength(str) {
        var len = 0;
        for (var i = 0; i < str.length; i++) {
        	
            if (escape(str.charAt(i)).length == 6) {
                len++;
            }
            
            len++;
        }
        return len;
    } // 문자 바이트 체크 끝
	
	// SMS 발송 체크박스 클릭 시
	$("#smsYN_checkbox").click(function() {
		
		var chk = $("#smsYN_checkbox").prop("checked");
		
		if(chk) {
			$("#smsContents").show();
		} else {
			$("#smsContents").hide();
		}
		
	});
	
	// 푸시 발송하기 버튼 클릭시
	$("#sendPush").click(function() {
		
		var chk = $("#smsYN_checkbox").prop("checked");
		
		if(chk) {
			$("#smsYN").val("Y");
		} else {
			$("#sms_contents").val("");
		}
		
		if(confirm("발송 하시겠습니까?")) {
    		$("#pushSendForm").submit();
    	}
	});
	
});


</script>

<title>PUSH2U - PUSH 발송</title>
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

<div class="col-md-12" style="height: 70%">
	<form id="pushSendForm" action="sendPush" method="post">
		<div class="page-header col-md-6 col-md-offset-3" style="margin-top: 0px;">
	          <h2>PUSH 발송</h2>
	    </div>
		<div class="col-md-6 col-md-offset-3" style="margin-bottom: 20px; ">
			<div class="col-md-6 ">
				<label for="push_popup" style="font-size: 20px">푸시 팝업 / 상태창</label>
				<div id="push_popup" style="margin-bottom: 20px;">
					<input type="text" class="form-control" id="push_title" name="push_title" placeholder="타이틀 입력" style="margin-bottom: 10px;">
					<textarea id="popup_contents" name="popup_contents" class="form-control" style="resize:none;" rows="3" placeholder="푸시 팝업 내용"></textarea>
					<span id="popup_byteInfo">0</span>/80Bytes
				</div>
				
				<label for="inapp_msg" style="font-size: 20px">앱 내 메시지</label>
				<div id="inapp_msg" style="margin-bottom: 20px;">
					<textarea id="inapp_contents" name="inapp_contents" class="form-control" style="resize:none;" rows="5" placeholder="앱 내 메시지"></textarea>
					<span id="inaap_byteInfo">0</span>/3500Bytes
				</div>

				<div class="checkbox">
					<label> 
						<input type="checkbox" id="smsYN_checkbox" name="smsYN_checkbox" > 푸시 실패 시 SMS 발송
					</label>
				</div>
				
				<div id="smsContents" style="margin-bottom: 20px; display: none;">
					<textarea id="sms_contents" name="sms_contents" class="form-control" style="resize:none;" rows="3" placeholder="SMS 내용"></textarea>
					<span id="sms_byteInfo">0</span>/90Bytes
				</div>
			</div>
			<div class="col-md-6">
				<label for="preview" style="font-size: 20px">미리보기</label>
				
				<div id="preview">
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
							<img src="/resources/images/android_noti.png">
							<div style="position:absolute; left:80px; top:265px; height:15px; width: 100%;">
								<input type="text" id="preview_title" value="" style="background-color: black; color: white; border-color: black; font-size: 12px; width: 80%; " disabled="disabled">
							</div>
							<div style="position:absolute; left:80px; top:290px; height:15px; width:100%;">
								<input type="text" id="popup_preview" value="" style="background-color: black; color: white; border-color: black; font-size: 12px; width: 80%;" disabled="disabled">
							</div>
						</div>
					</div>
					<div id="push_preview_ui" style="display: none;">
						<div id="preview_background">
							<img src="/resources/images/adroid_push.png">
							<div style="position:absolute; left:120px; top:200px; height:15px; width: 100%;">
								<textarea id="push_preview" rows="4" cols="28" style="resize: none; border-color: white; background-color: transparent;" disabled="disabled"></textarea>
							</div>
						</div>
					</div>
					<div id="inapp_preview_ui" style="display: none;">
						<div id="preview_background">
							<img src="/resources/images/android_inapp.png">
							<div style="position:absolute; left:20px; top:120px; height:300px; width: 100%;">
								<textarea id="inapp_preview" rows="10" cols="50" style="resize: none; background-color: transparent; border-color: black; color: white; font-size: medium;" disabled="disabled"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-md-12">
			<input type="hidden" id="smsYN" name="smsYN" value="N">
		
			<button id="sendPush" type="button" class="btn btn-info btn-lg center-block"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>  PUSH 발송하기</button>
		</div>
	</form>
	
</div>


</body>
</html>