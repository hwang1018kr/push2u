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
        elPlaceHolder: "smarteditor",
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
     
    //전송버튼 클릭이벤트
    $("#sendPush").click(function(){
        //id가 smarteditor인 textarea에 에디터에서 대입
        editor_object.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
         
        // 이부분에 에디터 validation 검증
         
        //폼 submit
        $("#pushRichSendForm").submit();
    })
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

<div class="col-md-12" style="height: 70%">
	<form id="pushRichSendForm" action="sendPushRich" method="post">
		<div class="page-header col-md-8 col-md-offset-2">
	          <h2>PUSH 발송</h2>
	    </div>
		<div class="col-md-8 col-md-offset-2" style="margin-bottom: 20px; ">
			<div class="col-md-8 ">
				<label for="push_popup" style="font-size: 20px">푸시 팝업 / 상태창</label>
				<div id="push_popup" style="margin-bottom: 20px;">
					<input type="text" class="form-control" id="push_title" name="push_title" placeholder="타이틀 입력" style="margin-bottom: 10px;">
					<textarea id="popup_contents" name="popup_contents" class="form-control" style="resize:none;" rows="3" placeholder="푸시 팝업 내용"></textarea>
					<span id="popup_byteInfo">0</span>/80Bytes
				</div>
				
				
				<label for="inapp_msg" style="font-size: 20px">앱 내 메시지</label>
				<!-- <form action="/send.jsp" method="post" id="frm"> -->
				<div id="inapp_msg" style="margin-bottom: 20px;">
				    <textarea name="smarteditor" id="smarteditor" rows="10" cols="100" style="width:500px; height:300px;"></textarea>
				    <input type="button" id="savebutton" value="서버전송" />
				</div>
				    
				<div class="checkbox">
					<label> 
						<input type="checkbox"> 푸시 실패 시 SMS 발송
					</label>
				</div>
			</div>
			<div class="col-md-6">
				
			</div>
		</div>
		<div class="col-md-12">
			<button id="sendPush" type="submit" class="btn btn-info btn-lg center-block"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>  PUSH 발송하기</button>
		</div>
	</form>
	
</div>


</body>
</html>