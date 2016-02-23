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


$(function() {
	
    setOpenGraph();
});

function setOpenGraph() {
	$.ajax({
		
		type: 'post',
		url: 'getTotalOpenGrapgh',
		dataType: 'json',
		data: { 'userId' : "${userId }" },
		success: function(result) {
			
			var pushGraph;
			var pushGraphOption = {
				 chart: {
					 renderTo: 'open_graph_container',
			         type: 'areaspline'
			     },
			     title: {
			            text: '시간대별 오픈/클릭률 '
		         },
		         subtitle: {
		             text: '${userId }님의 푸시발송에 대한 시간별 오픈/클릭 그래프'
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

						 '00시~07시',
		                 '08시~09시',
		                 '09시~10시',
		                 '10시~11시',
		                 '11시~12시',
		                 '12시~13시',
		                 '13시~14시',
		                 '14시~15시',
		                 '15시~16시',
		                 '16시~17시',
		                 '17시~18시',
		                 '18시~19시',
		                 '19시~20시',
		                 '20시~21시',
		                 '21시~22시',
		                 '22시~23시',
		                 '23시~24시',
						
		             ]
		         },
		         yAxis: {
		            title: {
		                text: '오픈/클릭률'
		            },
		            min: 0,
		            tickAmount: 5
		         },
		         tooltip: {
		            shared: true,
		            valueSuffix: ' %'
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
				var CNT_00_07 = 0;
				openCNT_00_07 = openMap.CNT_00 + openMap.CNT_01 + openMap.CNT_02 + openMap.CNT_03 + openMap.CNT_04 + openMap.CNT_05 + openMap.CNT_06 + openMap.CNT_07;
				
				openValueList.push(Number(CNT_00_07));
				openValueList.push(Number(openMap.CNT_08));
				openValueList.push(Number(openMap.CNT_09));
				openValueList.push(Number(openMap.CNT_10));
				openValueList.push(Number(openMap.CNT_11));
				openValueList.push(Number(openMap.CNT_12));
				openValueList.push(Number(openMap.CNT_13));
				openValueList.push(Number(openMap.CNT_14));
				openValueList.push(Number(openMap.CNT_15));
				openValueList.push(Number(openMap.CNT_16));
				openValueList.push(Number(openMap.CNT_17));
				openValueList.push(Number(openMap.CNT_18));
				openValueList.push(Number(openMap.CNT_19));
				openValueList.push(Number(openMap.CNT_20));
				openValueList.push(Number(openMap.CNT_21));
				openValueList.push(Number(openMap.CNT_22));
				openValueList.push(Number(openMap.CNT_23));

				for( var i = 0; i < openValueList.length; i++ ) {
					pushOpenSeries.data.push(openValueList[i]);
				}
				
				var clickValueList = new Array();
				var clickCNT_00_07 = 0;
				clickCNT_00_07 = clickMap.CNT_00 + clickMap.CNT_01 + clickMap.CNT_02 + clickMap.CNT_03 + clickMap.CNT_04 + clickMap.CNT_05 + clickMap.CNT_06 + clickMap.CNT_07;

				clickValueList.push(Number(clickCNT_00_07));
				clickValueList.push(Number(clickMap.CNT_08));
				clickValueList.push(Number(clickMap.CNT_09));
				clickValueList.push(Number(clickMap.CNT_10));
				clickValueList.push(Number(clickMap.CNT_11));
				clickValueList.push(Number(clickMap.CNT_12));
				clickValueList.push(Number(clickMap.CNT_13));
				clickValueList.push(Number(clickMap.CNT_14));
				clickValueList.push(Number(clickMap.CNT_15));
				clickValueList.push(Number(clickMap.CNT_16));
				clickValueList.push(Number(clickMap.CNT_17));
				clickValueList.push(Number(clickMap.CNT_18));
				clickValueList.push(Number(clickMap.CNT_19));
				clickValueList.push(Number(clickMap.CNT_20));
				clickValueList.push(Number(clickMap.CNT_21));
				clickValueList.push(Number(clickMap.CNT_22));
				clickValueList.push(Number(clickMap.CNT_23));
				
					
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
<!-- 	<div class="page-header col-md-8 col-md-offset-2"> -->
<!--           <h2>상세 레포트</h2> -->
<!--           <ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;"> -->
<!-- 			  <li class="active">레포트</li> -->
<!-- 			  <li class="active">상세 레포트</li> -->
<!-- 		  </ol> -->
<!--     </div> -->
 
	<div class="col-md-8 col-md-offset-2" style="margin-bottom: 20px;">
		<div id="open_graph_container" class="col-md-12 table_container" style="padding: 15px;">
		</div>
	</div>	

</div>

</body>

</html>