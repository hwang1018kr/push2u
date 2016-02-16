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


<script type="text/javascript" src="/resources/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>

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

$(function(){
    //전역변수선언
    var editor_object = [];
    
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: editor_object,
        elPlaceHolder: "pushEditor",
        sSkinURI: "/resources/smarteditor/SmartEditor2Skin.html", 
        htParams : {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,             
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : true,     
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true, 
        }
    });
    
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: editor_object,
        elPlaceHolder: "inappEditor",
        sSkinURI: "/resources/smarteditor/SmartEditor2Skin2.html", 
        htParams : {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,             
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : true,     
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true, 
        }
    }); 
    
 	// 푸시 타이틀 미리보기 입력
	$("#push_title").keyup(function() {
		
		limitByte(this, 40, "title");
		
	});
	
	// 상태창 내용 미리보기 입력
	$("#popup_contents").keyup(function() {
		
		limitByte(this, 80, "status");
		
	});
	
	// 푸시 팝업 미리보기 입력
	$(".se2_inputarea").keyup(function() {
		
		alert("호출");
		
		limitByte(this, 3500, "popup");
		
	});
	
	// 앱 내 메시지 미리보기 입력
	$(".se2_inputarea").keyup(function() {
		
		alert("호출");
		
		limitByte(this, 3500, "inapp");
		
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
        } else if (type == "popup") {
        	$('#popup_byteInfo').text(getTextLength(text));
        } else if (type == "inapp") {
        	$('#inapp_byteInfo').text(getTextLength(text));
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
    
    // 스마트에디터 popup 영역 글자수 체크
    /* function popup_count_text() { 
    	
        oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []); 

        var context = oEditors.getById["ir1"].getIR(); 

            if (context.length >= 1000) { 
                alert("내용은 1000 자 이하를 입력하세요."); 
                oEditors[0].exec("FOCUS", []); 
                return false; 
            } 
    }  */
    
 	// 스마트에디터 inapp 영역 글자수 체크
    /* function inapp_count_text() { 
 		
        oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []); 

        var context = oEditors.getById["ir1"].getIR(); 

            if (context.length >= 1000) { 
                alert("내용은 1000 자 이하를 입력하세요."); 
                oEditors[0].exec("FOCUS", []); 
                return false; 
            } 
    }  */
     
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
        //id가 smarteditor인 textarea에 에디터에서 대입
        editor_object.getById["pushEditor"].exec("UPDATE_CONTENTS_FIELD", []);
        editor_object.getById["inappEditor"].exec("UPDATE_CONTENTS_FIELD", []); 
        // 이부분에 에디터 validation 검증
        
        var chk = $("#smsYN_checkbox").prop("checked");
		
		if(chk) {
			$("#smsYN").val("Y");
		} else {
			$("#sms_contents").val("");
		}
		
		if(confirm("발송 하시겠습니까?")) {
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
	<form id="pushRichSendForm" action="sendPushRich" method="post">
		<div class="page-header col-md-8 col-md-offset-2" style="margin-top: 0px;">
	          <h2>RICH PUSH 발송</h2>
	    </div>
		<div class="col-md-8 col-md-offset-2" style="margin-bottom: 20px; ">
			<div class="col-md-8 ">
				<label for="push_popup" style="font-size: 20px">푸시 팝업 / 상태창</label>
				<div id="push_popup" style="margin-bottom: 20px;">
					<input type="text" class="form-control" id="push_title" name="push_title" placeholder="타이틀 입력" style="margin-bottom: 10px;">
					<textarea id="popup_contents" name="popup_contents" class="form-control" style="resize:none;" rows="3" placeholder="상태창 내용"></textarea>
					<span id="status_byteInfo">0</span>/80Bytes
				</div>

				<div id="push_msg" style="margin-bottom: 20px;">
				    <textarea name="pushEditor" id="pushEditor" rows="10" cols="100" style="width:490px; height:100px;"></textarea>
				    <span id="popup_byteInfo">0</span>/3500Bytes
				</div>
				    
				<label for="inapp_msg" style="font-size: 20px">앱 내 메시지</label>
				<div id="inapp_msg" style="margin-bottom: 20px;">
				    <textarea name="inappEditor" id="inappEditor" rows="10" cols="100" style="width:490px; height:100px;"></textarea>
				    <span id="inapp_byteInfo">0</span>/3500Bytes
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
			<div class="col-md-6">
				
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