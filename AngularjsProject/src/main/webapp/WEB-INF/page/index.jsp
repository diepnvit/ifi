<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Webservice application</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css"/>
<link rel="stylesheet"
  href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />

<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<script type="text/javascript" src="/js/angular.min.js"></script>
<script type="text/javascript" src="/js/angular-ui-router.js"></script>
<script type="text/javascript" src="/js/localforage.min.js"></script>
<script type="text/javascript" src="/js/ngStorage.min.js"></script>
<script type="text/javascript" src="/js/dirPagination.js"></script>
<script type="text/javascript" src="/js/main.js"></script>

<script type="text/javascript" src="/js/personal/PersonalService.js"></script>
<script type="text/javascript" src="/js/personal/PersonalController.js"></script>

<script type="text/javascript" src="/js/project/ProjectService.js"></script>
<script type="text/javascript" src="/js/project/ProjectController.js"></script>

<script type="text/javascript" src="/js/timesheet/TimesheetService.js"></script>
<script type="text/javascript" src="/js/timesheet/TimesheetController.js"></script>

</head>
<body ng-app="myApp">
	<div class="topnav" id="myTopnav">
		<a ui-sref="home" ui-sref-active="active">Home</a> 
		<a ui-sref="personal" ui-sref-active="active">Personal</a>
		<a ui-sref="project" ui-sref-active="active">Project</a> 
		<a ui-sref="timesheet" ui-sref-active="active">Timesheets</a>
		<a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
	</div>

	<div class="main" ui-view>
<!-- 		<ui-view></ui-view> -->
	</div>

<script>
function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}
</script>



</body>
</html>