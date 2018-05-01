
'use strict';
angular.module('myApp').controller('TimesheetController', ['TimesheetService','$window','$scope',function(TimesheetService,$window,$scope){
	var self = this;
	self.timesheet = {};
	self.timesheets = [];
	self.personals = [];
	
	self.submit = submit;
	self.getAllTimesheets = getAllTimesheets;
	self.getAllPersonals = getAllPersonals;
	self.getAllProjects = getAllProjects;
	self.createTimesheet = createTimesheet;
	self.updateTimesheet = updateTimesheet;
	self.removeTimesheet = removeTimesheet;
	self.confirm = confirm;
	self.timesheetToSubmit = timesheetToSubmit;
	self.reset = reset;
	
	
	self.successMessage = '';
	self.errorMessage = '';
	self.done = false;
	

	
	function submit(){
		if(self.timesheet.idTimesheet === undefined || self.timesheet.idTimesheet === null){
			createTimesheet(self.timesheet);
		}else{
			updateTimesheet(self.timesheet.idTimesheet,self.timesheet);
		}
	}
	
	function getAllTimesheets(){
		return TimesheetService.getAllTimesheets();
	}
	function getAllPersonals(){
		return TimesheetService.getAllPersonals();
	}
	
	function getAllProjects(){
		return TimesheetService.getAllProjects();
	}
	
	
	function createTimesheet(timesheet) {
		TimesheetService.createTimesheet(timesheet).then(
				function(response) {
					self.successMessage = 'Timesheet created successfully';
					self.errorMessage = '';
					self.done = true;
					self.timesheet = {};
					$scope.timesheetForm.$setPristine();
				},function(errResponse){
					self.errorMessage = 'Error while createing Timesheet: '+ errResponse;
					self.successMessage = '';
					console.log(timesheet.personal.id);
				}
		);
		
	}
	
	function updateTimesheet(id,timesheet) {
		TimesheetService.updateTimesheet(id, timesheet).then(
				function(response){
					self.successMessage = 'Timesheet updated successfully';
					self.errorMessage = '';
					self.done = true;
					self.timesheet = {};
					$scope.timesheetForm.$setPristine();
				}, function(errResponse){
					self.errorMessage = 'Error while updateing Timesheet: '+errResponse;
					self.successMessage = '';
				}
		);
	}
	function removeTimesheet(id) {
		
		TimesheetService.removeTimesheet(id).then(
					function (response) {
						self.successMessage = 'Timesheet deleted successfully';
						self.errorMessage = '';
						self.done = true;
					}
			);
		
	}
	
	function confirm(id) {
		if ($window.confirm("Are you want to delete it?")) {
			removeProject(id);
        }
	}
	
	function timesheetToSubmit(id) {
		self.successMessage = '';
		self.errorMessage = '';
		TimesheetService.getTimesheet(id).then(
				function(timesheet) {
					self.timesheet = timesheet;
				}
		);
	}
	function reset(){
        self.successMessage='';
        self.errorMessage='';
        self.timesheet={};
        $scope.timesheetForm.$setPristine();
    }
}])


