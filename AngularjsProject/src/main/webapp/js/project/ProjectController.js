'use strict';
angular
		.module('myApp')
		.controller(
				'ProjectController',
				[
						'ProjectService',
						'$window',
						'$scope',
						function(ProjectService,$window, $scope) {
							var self = this;
							self.project = {};
							self.projects = [];

							self.submit = submit;
							self.getAllProjects = getAllProjects;
							self.createProject = createProject;
							self.updateProject = updateProject;
							self.removeProject = removeProject;
							self.confirm = confirm;
							self.projectToSubmit = projectToSubmit;
							self.reset = reset;

							self.successMessage = '';
							self.errorMessage = '';
							self.done = false;
							
							$scope.$watch('ctrl.project.startDate', validateDates);
							$scope.$watch('ctrl.project.endDate', validateDates);
							 
							function validateDates() {
							    if (!$scope.model) return;
							    if ($scope.projectForm.startDate.$error.invalidDate || $scope.projectForm.endDate.$error.invalidDate) {
							        $scope.projectForm.startDate.$setValidity("endBeforeStart", true);  //already invalid (per validDate directive)
							    } else {
							        //depending on whether the user used the date picker or typed it, this will be different (text or date type).  
							        //creating a new date object takes care of that.  
							        var endDate = new Date($scope.ctrl.project.startDate);
							        var startDate = new Date($scope.ctrl.project.endDate);
							        $scope.projectForm.startDate.$setValidity("endBeforeStart", endDate >= startDate);
							    }
							}

							function submit() {
								if (self.project.id === undefined
										|| self.project.id === null) {
									createProject(self.project);
								} else {
									updateProject(self.project.id, self.project);
								}
							}

							function getAllProjects() {
								return ProjectService.getAllProjects();
							}

							function createProject(project) {
								ProjectService
										.createProject(project)
										.then(
												function(response) {
													self.successMessage = 'Project created successfully';
													self.errorMessage = '';
													self.done = true;
													self.project = {};
													$scope.projectForm
															.$setPristine();
												},
												function(errResponse) {
													self.errorMessage = 'Error while createing Project: '
															+ errResponse;
													self.successMessage = '';
												});

							}

							function updateProject(id, project) {
								ProjectService
										.updateProject(id, project)
										.then(
												function(response) {
													self.successMessage = 'Project updated successfully';
													self.errorMessage = '';
													self.done = true;
													$scope.projectForm
															.$setPristine();
												},
												function(errResponse) {
													self.errorMessage = 'Error while updateing Project: '
															+ errResponse;
													self.successMessage = '';
												});
							}
							function removeProject(id) {

								ProjectService
										.removeProject(id)
										.then(
												function(response) {
													self.successMessage = 'Project deleted successfully';
													self.errorMessage = '';
													self.done = true;
												});

							}

							function projectToSubmit(id) {
								self.successMessage = '';
								self.errorMessage = '';
								ProjectService.getProject(id).then(
										function(project) {
											self.project = project;
											self.project.endDate = new Date(
													self.project.endDate);
											self.project.startDate = new Date(
													self.project.startDate);
										});
							}

							function confirm(id) {
								if ($window.confirm("Are you want to delete it?")) {
									removeProject(id);
				                }
							}
							function reset() {
								self.successMessage = '';
								self.errorMessage = '';
								self.project = {};
								$scope.projectForm.$setPristine();
							}
						} ])
