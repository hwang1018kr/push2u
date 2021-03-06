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

<link rel="stylesheet" href="/resources/ckeditor/samples/css/samples.css">

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

.popup_button {
	bottom : 2%;
}
</style>

<script type="text/javascript">

$(function(){
	
	// 재발송 됐을 경우 실행
	if("${resendcamId }") {
		
		$.ajax({
			
			type     : "post",
			url      : "getRecentTemplete",
			data     : {"camId" : "${resendcamId }"},
			dataType : "json",//text,xml, json
			success  : function(result) {
				
				$("#push_title").val(result.PUSH_TITLE);
				$("#status_contents").val(result.PUSH_MSG);
				CKEDITOR.instances.pushEditor.setData(result.POPUP_CONTENT);
				CKEDITOR.instances.inappEditor.setData(result.INAPP_CONTENT);
				
				$("#preview_title").val(result.PUSH_TITLE);
				$("#status_preview").val(result.PUSH_MSG);
				
				$("#rich_popup").html("");
	        	$("#rich_popup").html(result.POPUP_CONTENT);
	        	
	        	$("#rich_popup").append('<button class="popup_button" style="position: absolute; background-color: gray; color: white; font-size : 13px; left:70px; height:30px; width: 60px;" disabled="disabled">닫기</button><button class="popup_button" style="position: absolute; background-color: gray; color: white; font-size : 13px; left:140px; height:30px; width: 60px;" disabled="disabled">보기</button>');

	        	$("#rich_inapp").html("");
	        	$("#rich_inapp").html(result.INAPP_CONTENT);
	        	
	        	limitByte($("#push_title"), 40, "title");
	        	limitByte($("#status_contents"), 80, "status");
	        	
				
			},
		    error : function(xhr, error){
		    	alert(xhr.status + ", " + error);
		    	alert(xhr.responseText);
		    }
		});
		
		//return false;
	}
	
	//최근 메시지 제목 클릭시
	$("#recent_push").change(function(){
		
		var camId  = $("#recent_push option:selected").attr('id');
		
		if( camId == null || camId == "" ) {
			return ;
			
		} else if (camId == "no-use") {
			$("#push_title").val("");
			$("#status_contents").val("");
			$("#preview_title").val("");
			$("#status_preview").val("");
			
			CKEDITOR.instances.pushEditor.setData("");
			CKEDITOR.instances.inappEditor.setData("");
			
			return;
		}
		
		$.ajax({
			
			type     : "post",
			url      : "getRecentTemplete",
			data     : {"camId" : camId},
			dataType : "json",//text,xml, json
			success  : function(result) {
				
				$("#push_title").val(result.PUSH_TITLE);
				$("#status_contents").val(result.PUSH_MSG);
				CKEDITOR.instances.pushEditor.setData(result.POPUP_CONTENT);
				CKEDITOR.instances.inappEditor.setData(result.INAPP_CONTENT);
				
				$("#preview_title").val(result.PUSH_TITLE);
				$("#status_preview").val(result.PUSH_MSG);
				
				limitByte($("#push_title"), 40, "title");
	        	limitByte($("#status_contents"), 80, "status");
				
			},
		    error : function(xhr, error){
		    	alert(xhr.status + ", " + error);
		    	alert(xhr.responseText);
		    }
		});
		
		return false;
	});

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
		
		limitByte(this, 40, "title");
		
		var title = $("#push_title").val();
		
		$("#preview_title").val("");
		$("#preview_title").val(title);
		
	});
	
	// 상태창 메시지 미리보기 입력
	$("#status_contents").keyup(function() {
		
		limitByte(this, 80, "status");
		
		var title = $("#status_contents").val();
		
		$("#status_preview").val("");
		$("#status_preview").val(title);
		
	});

	// CKEDITOR keyup(푸시 팝업 미리보기)
	CKEDITOR.instances["pushEditor"].on("instanceReady", function(){
		CKEDITOR.instances.pushEditor.on("change", function() {
        	ckLimitByte(CKEDITOR.instances.pushEditor, 3500, "popup");
        });
 	});
	
	// CKEDITOR keyup(인앱 미리보기)
	CKEDITOR.instances["inappEditor"].on("instanceReady", function(){
		CKEDITOR.instances.inappEditor.on("change", function() {
        	ckLimitByte(CKEDITOR.instances.inappEditor, 3500, "inapp");
        });
 	});
	
	// SMS 글자수 제한
	$("#sms_contents").keyup(function() {
		limitByte(this, 90, "sms");
	});
	
	// 바이트 제한 함수
	function limitByte(obj, bytes, type){

		var text = $(obj).val();
        var leng = text.length;
        while(getTextLength(text) > bytes) {
        	
            leng--;
            text = text.substring(0, leng);
            
        }
        $(obj).val(text);
        
        if (type == "status") {
        	$('#status_byteInfo').text(getTextLength(text));
        } else if (type == "sms") {
        	$('#sms_byteInfo').text(getTextLength(text));
        }
    }
	
	// 바이트 제한 함수(ckeditor)
	function ckLimitByte(obj, bytes, type){
		
        var text = obj.getData();
        var leng = text.length;
        
        while(getTextLength(text) > bytes) {
        	
            leng--;
            text = text.substring(0, leng);
        }
        
        if (type == "popup") {
        	
        	$("#rich_popup").html("");
        	$("#rich_popup").html(text);
        	
        	$("#rich_popup").append('<button class="popup_button" style="position: absolute; background-color: gray; color: white; font-size : 13px; left:70px; height:30px; width: 60px;" disabled="disabled">닫기</button><button class="popup_button" style="position: absolute; background-color: gray; color: white; font-size : 13px; left:140px; height:30px; width: 60px;" disabled="disabled">보기</button>');

        } else if (type == "inapp") {
        	
        	$("#rich_inapp").html("");
        	$("#rich_inapp").html(text);
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
	
    //전송버튼 클릭이벤트
    $("#sendPush").click(function(){
    	
    	var statusContent = $("#status_contents").val();
    	var pushContent   = CKEDITOR.instances.pushEditor.getData();
    	var inappContent  = CKEDITOR.instances.inappEditor.getData();
    	
    	statusContent = statusContent.replace(/<script(.*?)>([^]+)<\/script>/gi, ""); // script 제거
    	pushContent   = pushContent.replace(/<script(.*?)>([^]+)<\/script>/gi, ""); // script 제거
    	inappContent  = inappContent.replace(/<script(.*?)>([^]+)<\/script>/gi, ""); // script 제거
    	
    	if($("#push_title").val() == null || $("#push_title").val() == "") {
    		
			alert("푸시 타이틀을 입력해주세요!");
			$("#push_title").focus();
			
			return false;
			
		} else if($("#status_contents").val() == null || $("#status_contents").val() == "") {
			
			alert("푸시 상태창 내용을 입력해주세요!");
			$("#status_contents").focus();
			
			return false;
			
		} else if(pushContent == null || pushContent =="") {
			
			alert("푸시 팝업 내용을 입력해주세요!");
			CKEDITOR.instances.pushEditor.focus();
			
			return false;
			
		} else if(inappContent == null || inappContent =="") {
			
			alert("앱 내 메시지 내용을 입력해주세요!");
			CKEDITOR.instances.inappEditor.focus();
			
			return false;
			
		}
    	
        var chk = $("#smsYN_checkbox").prop("checked");
		
		if(chk) {
			$("#smsYN").val("Y");
			
			if($("#sms_contents").val() == null || $("#sms_contents").val() == "") {
				alert("SMS 내용을 작성해주세요.");
				$("#sms_contents").focus();
				
				return false;
			}
			
		} else {
			$("#sms_contents").val("");
		}
		
		if(confirm("발송 하시겠습니까?")) {
			
			$("#status_contents").val(statusContent);
			CKEDITOR.instances.pushEditor.setData(pushContent);
			CKEDITOR.instances.inappEditor.setData(inappContent);
			
			//폼 submit
	        $("#pushRichSendForm").submit();
    	}
        
    });
    
})
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

<div class="col-md-12" style="height: 70%">
	<form id="pushRichSendForm" action="sendPushRich" method="post">
		<div class="page-header col-md-8 col-md-offset-2" style="margin-top: 0px;">
	          <h2>Rich Push 발송</h2>
	          <ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;">
	          	  <li class="active">홈</li>
				  <li class="active">PUSH 발송</li>
				  <li class="active">Rich Push 발송</li>
			  </ol>
	    </div>
	   
		<div class="col-md-10 col-md-offset-1" style="margin-bottom: 20px; ">
		    <div class="col-md-6  col-md-offset-1 " style="margin-bottom: 20px; ">
		    	<label for="recent_push" style="font-size: 20px">최근 메시지 불러오기</label>
		    	<select id="recent_push" class="form-control" >
		    		<option value="no-use" id="no-use" selected>미사용</option>
		    		<c:forEach var="list" items="${recentList }">
		    			<option class="recent_list" id="${list.CAM_ID }">${list.PUSH_TITLE }</option>
		    		</c:forEach>
		    	</select>
	    	</div>
		    
			<div class="col-md-6  col-md-offset-1">
				<label for="push_popup" style="font-size: 20px">푸시 팝업 / 상태창</label>
				<div id="push_popup" style="margin-bottom: 20px;">
					<input type="text" class="form-control" id="push_title" name="push_title" placeholder="타이틀 입력" style="margin-bottom: 10px;" maxlength="20">
					<textarea id="status_contents" name="status_contents" class="form-control" style="resize:none;" rows="3" placeholder="상태창 내용" maxlength="80"></textarea>
					<span id="status_byteInfo">0</span>/80Bytes
				</div>
				
				<div id="push_msg" style="margin-bottom: 20px;">
				  <!--  <textarea name="pushEditor" id="pushEditor" rows="10" cols="100" style="width:490px; height:100px;"></textarea> -->
				  <textarea class="ckeditor" cols="20" id="pushEditor" name="pushEditor" rows="15"></textarea>
				    <!-- <span id="popup_byteInfo">0</span>/3500Bytes -->
				</div>
				    
				<label for="inapp_msg" style="font-size: 20px">앱 내 메시지</label>
				<div id="inapp_msg" style="margin-bottom: 20px;">
				   <!-- <textarea name="inappEditor" id="inappEditor" rows="10" cols="100" style="width:490px; height:100px;"></textarea> -->
					<textarea class="ckeditor" cols="20" id="inappEditor" name="inappEditor" rows="15"></textarea>
				    <!-- <span id="inapp_byteInfo">0</span>/3500Bytes -->
				</div>    
				    
				<div class="checkbox">
					<label> 
						<input type="checkbox" id="smsYN_checkbox"> 푸시 실패 시 SMS 발송
					</label>
				</div>
				
				<div id="smsContents" style="margin-bottom: 20px; display: none;">
					<textarea id="sms_contents" name="sms_contents" class="form-control" style="resize:none;" rows="3" placeholder="SMS 내용"></textarea>
					<span id="sms_byteInfo">0</span>/90Bytes
				</div>
			</div>
			
			<div class="col-md-5" >
				<!-- <div id="preview_div" style="position:absolute;top:0px;left:0px;"> -->
				<!-- <div id="preview_div" > -->
			
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
								<img src="/resources/images/android_rich_status.png">
								<div style="position:absolute; left:90px; top:255px; height:15px; width: 310px;">
									<input type="text" id="preview_title" value="" style="background-color: black; color: white; border-color: black; font-size: 12px; width: 100%; " disabled="disabled">
								</div>
								<div style="position:absolute; left:90px; top:280px; height:15px; width:310px;">
									<input type="text" id="status_preview" value="" style="background-color: black; color: white; border-color: black; font-size: 12px; width: 100%; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;" disabled="disabled">
								</div>
							</div>
						</div>
						<div id="push_preview_ui" style="display: none;">
							<div id="preview_background">
								<img src="/resources/images/android_rich_popup.png">
								<div id="rich_popup" style="position:absolute; background-color : white; left:70px; top:200px; width: 280px; border-radius : 15px; padding: 15px; padding-bottom: 30px; word-break : break-all; min-height: 180px; max-height: 500px; overflow: auto;">
									<button class="popup_button" style="position: absolute; background-color: gray; color: white; font-size : 13px; left:70px;  height:30px; width: 60px;" disabled="disabled">닫기</button>
									<button class="popup_button" style="position: absolute; background-color: gray; color: white; font-size : 13px; left:140px; height:30px; width: 60px;" disabled="disabled">보기</button>
								</div>
							</div>
						</div>
						<div id="inapp_preview_ui" style="display: none;">
							<div id="preview_background">
								<img src="/resources/images/android_rich_inapp.png">
								<div id="rich_inapp" style="position:absolute; left:20px; top:120px; height:600px; width: 385px; word-break : break-all; color: white; overflow: auto;">
								
								</div>
							</div>
						</div>
					</div>
				<!-- </div> -->
			</div>
			
		</div>
		<div class="col-md-12">
			<input type="hidden" id="smsYN" name="smsYN" value="N">
		
			<button id="sendPush" type="button" class="btn btn-info btn-lg center-block"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>  PUSH 발송하기</button>
		
			<br><br><br><br>
		</div>
	</form>
	
</div>

</body>
</html>