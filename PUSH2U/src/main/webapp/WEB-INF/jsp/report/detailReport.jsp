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
<script type="text/javascript" src="/resources/Highcharts-4.2.3/js/highcharts.js"></script>


<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<style type="text/css">
.table_container {
    border: 1px solid gray;
    border-radius: 10px;
    position: relative;
}
</style>

<script type="text/javascript">
function popup(el){

	var url = "/push/" + el.id + "?camId=" + ${detailReport.CAM_ID };
	var name = "popup";
	var height = 500;
	var width = 1000;
	
	if (el.id == "pushView"){
		height = 800;
		width = 500;
	}
	
	var top = (screen.availHeight - height) / 2 - 50;
	var left = (screen.availWidth - width) / 2;
	
	window.open(url,name,"width=" + width + ",height=" + height + ",toorbar=no,status=no,location=no,scrollbars=yes,menubar=no,resizable=yes,left=" + left + ",top=" + top + "")
}

$(function() {
	
    setOpenGraph();
});

function setOpenGraph() {
	$.ajax({
		
		type: 'post',
		url: 'getOpenGraph',
		dataType: 'json',
		data: { 'camId' : "${camId }" },
		success: function(result) {
			
			var pushGraph;
			var pushGraphOption = {
				 chart: {
					 renderTo: 'open_graph_container',
			         type: 'areaspline'
			     },
			     title: {
			            text: '발송후 오픈/클릭 통계'
		         },
		         subtitle: {
		             text: '푸시 발송 후에 경과한 시간별 오픈/클릭 그래프'
		         },
		         legend: {
		             layout: 'vertical',
		             align: 'left',
		             verticalAlign: 'top',
		             x: 150,
		             y: 100,
		             floating: true,
		             borderWidth: 1,
		             backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
		         },
		         xAxis: {
		             categories: [
		                 '1Hour',
		                 '2Hour',
		                 '3Hour',
		                 '4Hour',
		                 '5Hour',
		                 '6Hour',
		                 '7Hour',
		                 '8Hour',
		                 '9Hour',
		                 '10Hour',
		                 '11Hour',
		                 '12Hour',
		             ]
		         },
		         yAxis: {
		            title: {
		                text: '오픈/클릭 수'
		            },
		            min: 0,
		            tickAmount: 5
		         },
		         tooltip: {
		            shared: true,
		            valueSuffix: ' 건'
		         },
		         credits: {
		            enabled: false
		         },
		         plotOptions: {
		            areaspline: {
		                fillOpacity: 0.6
		            }
		         },
		         series: []
			}
			
			if( result == null || result ==  "" ) {
				
				pushGraph = new Highcharts.Chart(pushGraphOption);
				pushGraph.showLoading(_BLANK_DATA_MESSAGE_);
				
			} else {
				
				var pushOpenSeries = {
						name : '오픈',
			            data : []
				};
				
				var pushClickSeries = {
						name : '클릭',
			            data : []
				};
				
				var openMap  = result.openMap;
				var clickMap = result.clickMap;
			
				var openValueList = new Array();
				
				openValueList.push(Number(openMap.CNT_1));
				openValueList.push(Number(openMap.CNT_2));
				openValueList.push(Number(openMap.CNT_3));
				openValueList.push(Number(openMap.CNT_4));
				openValueList.push(Number(openMap.CNT_5));
				openValueList.push(Number(openMap.CNT_6));
				openValueList.push(Number(openMap.CNT_7));
				openValueList.push(Number(openMap.CNT_8));
				openValueList.push(Number(openMap.CNT_9));
				openValueList.push(Number(openMap.CNT_10));
				openValueList.push(Number(openMap.CNT_11));
				openValueList.push(Number(openMap.CNT_12));
				
				for( var i = 0; i < openValueList.length; i++ ) {
					pushOpenSeries.data.push(openValueList[i]);
				}
				
				var clickValueList = new Array();
				
				clickValueList.push(Number(clickMap.CNT_1));
				clickValueList.push(Number(clickMap.CNT_2));
				clickValueList.push(Number(clickMap.CNT_3));
				clickValueList.push(Number(clickMap.CNT_4));
				clickValueList.push(Number(clickMap.CNT_5));
				clickValueList.push(Number(clickMap.CNT_6));
				clickValueList.push(Number(clickMap.CNT_7));
				clickValueList.push(Number(clickMap.CNT_8));
				clickValueList.push(Number(clickMap.CNT_9));
				clickValueList.push(Number(clickMap.CNT_10));
				clickValueList.push(Number(clickMap.CNT_11));
				clickValueList.push(Number(clickMap.CNT_12));
				
				for( var i = 0; i < clickValueList.length; i++ ) {
					pushClickSeries.data.push(clickValueList[i]);
				}
			}
			
			pushGraphOption.series.push(pushOpenSeries);
			pushGraphOption.series.push(pushClickSeries);
			
			pushGraph = new Highcharts.Chart(pushGraphOption);
			
		},
		error: function(xhr, error) {
			alert(xhr.status + ", " + error);
			alert("데이터를 가져오는 도중 오류가 발생했습니다.");
						
		}
		
	});
}

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
            	
            	<li><a href="/push/home"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>  홈</a></li>
            	<li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>  PUSH 발송<span class="caret"></span></a>
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
          <ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;">
          	  <li class="active">홈</li>
			  <li class="active">레포트</li>
			  <li class="active">상세 레포트</li>
		  </ol>
    </div>
    
    <!-- 상세 레포트 테이블 시작 -->
	<div class="col-md-8 col-md-offset-2" style="margin-bottom: 20px;">
		<div class="table-responsive">
		
			<div class="col-md-8" style="padding-left: 0px;">
				<label for="detailReport" style="font-size: 20px; text-align: center;">푸시 제목 : ${detailReport.PUSH_TITLE }</label>
				<a style="cursor:pointer; " id="pushView" onclick="popup(this)">
					<button style="margin-bottom: 5px; margin-left:10px; outline:none;" id="sendPush" type="button" class="btn btn-primary btn-sm">전문보기</button>
				</a>
			</div>
			<div class="col-md-4" style="padding-right: 1px;" align="right">
				<a style="cursor:pointer; outline-width: 0;" id="goList" href="reportView?pageNum=${pageNum }">
					<button style="margin-bottom: 5px; outline:none;" id="sendPush" type="button" class="btn btn-default btn-sm">목록으로</button>
				</a>
			</div>
			
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
	
	<div class="col-md-8 col-md-offset-2" style="margin-bottom: 20px;">
		
		<div id="open_graph_container" class="col-md-12 table_container" style="padding: 15px;">
			
		</div>
	
	</div>
	
	
	
</div>

</body>

</html>