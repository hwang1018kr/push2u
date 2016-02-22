<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- script -->
<script type="text/javascript" src="/resources/js/jquery-2.2.0.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<style type="text/css">
.table_container {
    border: 2px solid #2D6BA1;
    border-radius: 10px;
    position: relative;
}
</style>

<script type="text/javascript">
$(function() {
	
	var RegexName  = /^[가-힣a-zA-Z]{2,30}$/; //이름 유효성 검사 2~30자 사이
	var RegexId    = /^[a-z0-9_-]{8,15}$/; //아이디 유효성 검사 8~15자 사이
	var RegexPw    = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/; // 비밀번호 영문, 숫자, 특수기호 조합 8~15
	var RegexPhone = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/; //전화번호 유효성 검사
	
	var authNumber  = null;
	var authFlag    = "N";
	var idCheckFlag = "N";
	
	if(document.location.href == (parent.document.referrer+"?code=loginFail") || document.location.href == "http://localhost:8080/?code=loginFail") {
		alert("아이디와 비밀번호를 확인해주세요.");
	}
	
	// 아이디 중복검사하고 아이디 값 바꿀 경우 실행
	$("#input_id").keyup(function() {
		if(idCheckFlag == "Y" ) {
			
			alert("아이디 중복검사를 다시 해주세요.");
			
			idCheckFlag = "N";
			
			$("#idCheck_btn").attr("class", "btn btn-warning");
			$("#idCheck_btn").text("중복 검사");
		}
	});
	
	// 번호 인증하고 전화번호 값 바꿀 경우 실행
	$("#phone_num").keyup(function() {
		if(authFlag == "Y" ) {
			
			alert("번호 인증을 다시 해주세요.");
			
			authFlag = "N";
		}
	});
	
	$("#login_btn").click(function() {
		if(!$("#id").val() && !$("#passwd").val()) {
			
			alert("ID와 비밀번호를 입력해주세요.");
			
			return false;
		} 
	});
	
	// 아이디 중복검사 클릭 시
	$("#idCheck_btn").click(function() {
		
		if( !RegexId.test($.trim($("#input_id").val())) ) {
			
			alert("아이디는 영문 소문자, 숫자 조합, 8~15자 이어야 합니다.");
			return false;
		}
		
		var id = $("#input_id").val();
		
		if(id != null && id != "") {
			
			$.ajax({
				
				type     : "post",
				url      : "user/idCheck",
				data     : {"userId" : id},
				dataType : "text",//text,xml, json
				success  : function(result) {
					
					idCheck(result);
					
				},
			    error : function(xhr, error){
			    	alert(xhr.status + ", " + error);
			    	alert(xhr.responseText);
			    }
			});
			
			return false;
			
		} else {
			alert("ID를 입력해주세요.");
			return false;
		}
		
	}); // 아이디 중복 검사 끝
	
	// 아이디 중복검사 결과
	function idCheck(result) {
		
		if(result == "NON_EXIST") {
			
			alert("사용 가능한 아이디입니다.");
			
			idCheckFlag = "Y";
			
			$("#idCheck_btn").attr("class", "btn btn-success");
			$("#idCheck_btn").text("사용 가능");
			
		} else {
			
			alert("이미 존재하는 아이디입니다.");
			
			idCheckFlag = "N";
			
			$("#idCheck_btn").attr("class", "btn btn-warning");
			$("#idCheck_btn").text("중복 검사");
		}
		
	}
	
	// 휴대폰 인증 버튼 클릭 시
	$("#auth_btn").click(function() {
		
		if( !RegexPhone.test($.trim($("#phone_num").val())) ) {
			
			alert("번호는 특수 기호 없이 10~11자만 입력 가능합니다.");
			return false;
		}
		
		var phone_num = $("#phone_num").val();
		
		if(phone_num != null && phone_num != "") {
			
			$.ajax({
				
				type     : "post",
				url      : "user/auth",
				data     : {"phoneNum" : phone_num},
				dataType : "json",//text,xml, json
				success  : function(result) {
					
					authNumber = result;
					
					$("#phone_num").attr("readonly", "readonly");
					$("#auth_btn").attr("disabled", "disabled");
					
					alert("인증번호가 발송되었습니다.");
					
					timerStart();
					
				},
			    error : function(xhr, error){
			    	alert(xhr.status + ", " + error);
			    }
			});
			return false;
			
		} else {
			alert("휴대폰 번호를 입력해주세요.");
			return false;
		}
	}); // 휴대폰 번호 인증 끝
	
	
	// 타이머 시작
	function timerStart() {
		
		 var time = 180;
		 
		 display = $('#time');
		 startTimer(time, display);
	};
		
	var timeID;
	// 인증번호 3분 타이머 시작
	function startTimer(duration, display) {
			
		var timer = duration, minutes, seconds;
		
		timeID = setInterval(function() {
			
			minutes = parseInt(timer / 60, 10);
			seconds = parseInt(timer % 60, 10);

			minutes = minutes < 10 ? "0" + minutes : minutes;
			seconds = seconds < 10 ? "0" + seconds : seconds;

			display.text(minutes + ":" + seconds);

			if (--timer < 0) {
				
				clearInterval(timeID);
				display.text("");
				
				$("#phone_num").removeAttr("readonly");
				$("#auth_btn").removeAttr("disabled");
		   
				// 인증번호 삭제 요청
				authNumber = null;
				
				alert("인증 시간이 초과되었습니다.");
				
				return false;
			}
			
		}, 1000);
	}
		
	// 인증번호 입력 클릭 시
	$("#authCheck_btn").click(function() {
		
		if(authNumber == $("#user_auth").val()) {
			
			authFlag = "Y";
			
			alert("인증 되었습니다.");
			
			clearInterval(timeID);
			display.text("");
			
			$("#authEnter").remove();
			$("#auth_btn").text("인증 완료");
			$("#auth_btn").attr("class", "btn btn-success");
			
		} else {
			
			alert("인증번호가 일치하지 않습니다.");
			
		}
		
		return false;
	});
	
	// 가입하기 버튼 클릭 시 유효성 검사
	$("#join").click(function() {
		
		if(!$("#input_id").val()) {
			
			alert("ID를 입력해주세요");
			
			$("#input_id").focus();
			
			return false;
			
		} else if( !$("#input_passwd1").val() || !RegexPw.test($.trim($("#input_passwd1").val())) ) {
			
			alert("비밀번호는 영문, 숫자, 특수기호 조합 8~15자 이어야 합니다.");
			
			$("#input_passwd1").focus();
			
			return false;
			
		} else if(!$("#input_passwd2").val()) {
			
			alert("비밀번호를 한번 더 입력해주세요.");
			
			$("#input_passwd2").focus();
			
			return false;
			
		} else if(!$("#input_name").val() || !RegexName.test($.trim($("#input_name").val())) ) {
			
			alert("이름은 한글, 영문 2~30자 이어야 합니다.");
			
			$("#input_name").focus();
			
			return false;
			
		} else if($("#input_passwd1").val() != $("#input_passwd2").val()) {
			
			alert("비밀번호 확인을 다시 해주세요.");
			
			$("#input_passwd2").focus();
			
			return false;
			
		} else if(authFlag != "Y") {
			
			alert("휴대폰 인증을 해주세요.");
			
			return false;
			
		} else if(idCheckFlag != "Y") {
			
			alert("아이디 중복검사를 해주세요.");
			
			return false;
			
		}
		
		alert("가입을 축하드립니다.");
		
	}); // 가입하기 끝
	
});

</script>

<title>PUSH2U에 오신 것을 환영합니다!</title>
</head>
<body>

<div id="header" class="col-md-12" style="height: 20%">

</div>

<div id="login" class="col-md-12" style="height: 60%">

	<div id="login_box" class="col-md-4 col-md-offset-4 table_container form-group" style="height: 370px;">
		<div style="text-align: center; margin-bottom: 20px;">
			<h1><i class="fa fa-envelope"></i>PUSH2U</h1>
		</div>
		
		<form id="login_form" action="user/login" method="post">
			<div id="login_form" class="col-md-12" style="margin-bottom: 20px;">
				<input type="text" id="id" name="userId" class="form-control center-block" style="margin-bottom: 20px; width: 65%" placeholder="ID">
				<input type="password" id="passwd" name="userPasswd" class="form-control center-block" style="width: 65%" placeholder="PASSWORD">
			</div>
			
			<div id="btn_group" class="col-md-12">
				<div class="center-block" style="width: 65%; margin-bottom: 10px;">
					<button id="login_btn" type="submit" class="btn btn-primary btn-block">
						<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>   LOGIN
					</button>
				</div>
			</div>
		</form>
		
		<div class="col-md-12">
			<div class="center-block" style="width: 65%;">
				<button id="join_btn" type="button" class="btn btn-info btn-block" data-toggle="modal" data-target="#myModal">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>  JOIN
				</button>
			</div>
		</div>
	</div>
	
	<!-- 회원가입 모달 시작 -->
	<div id="myModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">회원가입</h4>
				</div>
				
				<form action="user/join" method="post">
				<div class="modal-body" style="height: 420px; overflow:auto;">
					<div class="col-md-10 col-md-offset-1">
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" id="input_id" name="userId" placeholder="아이디 (소문자, 숫자 조합 8~15자)">
								<span class="input-group-btn">
									<button id="idCheck_btn" class="btn btn-warning">
										중복 검사<i class="fa fa-mail-forward spaceLeft"></i>
									</button>
								</span>
							</div>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="input_passwd1" name="userPasswd" placeholder="비밀번호 (영문, 숫자, 특수기호 조합 8~15)">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="input_passwd2" placeholder="비밀번호 확인">
							<p class="help-block">비밀번호 확인을 위해 다시한번 입력 해 주세요</p>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="input_name" name="userName" placeholder="이름 (한글, 영문 2~30자)">
						</div>
						<div class="form-group">
							<label for="username">휴대폰 인증</label>
							<div class="input-group">
								<input type="tel" class="form-control" id="phone_num" name="phoneNum"
									placeholder="- 없이 입력해 주세요"> <span
									class="input-group-btn">
									<button id="auth_btn" class="btn btn-warning">
										인증번호 전송<i class="fa fa-mail-forward spaceLeft"></i>
									</button>
								</span>
							</div>
						</div>
						<div class="form-group" id="authEnter">
							<label for="username">인증번호 입력</label>
							<div class="input-group">
								<input type="text" class="form-control" id="user_auth"
									placeholder="인증번호 4자리"> <span class="input-group-btn">
									<button id="authCheck_btn" class="btn btn-info">
										인증번호 입력<i class="fa fa-edit spaceLeft"></i>
									</button>
								</span>
							</div>
							<div id="time" style="color: red; font-size: 15">
								
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
					<button id="join" type="submit" class="btn btn-info">가입하기</button>
					<button type="button" class="btn btn-warning" data-dismiss="modal">취소</button>
				</div>
				
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- 회원가입 모달 끝 -->
</div>

<div id="footer" class="col-md-12" style="height: 20%">

</div>
	
</body>
</html>