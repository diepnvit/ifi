var myApp = angular.module('myApp', [ 'ui.router','ngStorage']);


myApp.constant('urls', {
	BASE : 'http://localhost:8181',
	PERSONAL_SERVICE_API : 'http://localhost:8181/api/personal/',
	PROJECT_SERVICE_API : 'http://localhost:8181/api/project/',
	TIMESHEET_SERVICE_API : 'http://localhost:8181/api/timesheet/'
});

myApp.config(['$stateProvider','$urlRouterProvider',
	function($stateProvider, $urlRouterProvider) {
	var homeState = {
		name : 'home',
		url : '/',
		templateUrl : '/js/home/home.template.jsp'
	};
	
	var personalState = {
		name : 'personal',
		url : '/personal',
		templateUrl : '/js/personal/personal.template.jsp',
		controller : 'PersonalController',
		controllerAs : "ctrl",
		resolve : {
			personals: function($q, PersonalService) {
				var deferred = $q.defer();
				PersonalService.loadAllPersonals().then(deferred.resolve, deferred.resolve);
				return deferred.promise;
			}
		}
	};


	var projectState = {
		name : 'project',
		url : '/project',
		templateUrl : '/js/project/project.template.jsp',
		controller : 'ProjectController',
		controllerAs : "ctrl",
		resolve : {
			projects: function($q, ProjectService) {
				var deferred = $q.defer();
				ProjectService.loadAllProject().then(deferred.resolve, deferred.resolve);
				return deferred.promise;
			}
		}
	};

	var timesheetState = {
		name : 'timesheet',
		url : '/timesheet',
		templateUrl : '/js/timesheet/timesheet.template.jsp',
		controller : 'TimesheetController',
		controllerAs : "ctrl",
		resolve : {
			projects: function($q, TimesheetService) {
				var deferred = $q.defer();
				TimesheetService.loadAllTimesheet().then(deferred.resolve, deferred.resolve);
				return deferred.promise;
			}
		}
	};

	$stateProvider.state(personalState);
	$stateProvider.state(projectState);
	$stateProvider.state(timesheetState);
	$stateProvider.state(homeState);
	
	$urlRouterProvider.otherwise('/');
}]);
