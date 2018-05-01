/**
 * 
 */
'use strict';

angular.module('myApp').factory('ProjectService',['$localStorage','$http','$q','urls',
	function($localStorage,$http,$q,urls){
		var factory = {
				loadAllProject: loadAllProject,
				getAllProjects: getAllProjects,
				getProject: getProject,
				createProject:createProject,
				updateProject:updateProject,
				removeProject:removeProject
		};
		
		return factory;
		
		
		
		function loadAllProject(){
				var deferred = $q.defer();
				$http.get(urls.PROJECT_SERVICE_API)
				.then(function(response) {
					$localStorage.projects = response.data;
					deferred.resolve(response);
				},function(errResponse){
					deferred.reject(errResponse);
				}
			);
			return deferred.promise;
		}
		
		function getAllProjects() {
			return $localStorage.projects;
		}
		
		function getProject(id) {
			var deferred = $q.defer();
			$http.get(urls.PROJECT_SERVICE_API + id)
			.then(function(response) {
				deferred.resolve(response.data);
			}, function(errResponse) {
				deferred.reject(errResponse);
			});
			return deferred.promise;
		}
		
		function createProject(project) {
			var defferred = $q.defer();
			$http.post(urls.PROJECT_SERVICE_API, project).then(
					function(response) {
						loadAllProject();
						defferred.resolve(response.data);
					},function(errResponse){
						defferred.reject(errResponse);
					}
			);
			return defferred.promise;
		}
		
		function updateProject(id,project) {
			var defferred = $q.defer();
			$http.put(urls.PROJECT_SERVICE_API + id, project).then(
					function(response) {
						loadAllProject();
						defferred.resolve(response.data);
					},
					function(errResponse) {
						defferred.reject(errResponse);
					}
			);
			return defferred.promise;
		}
		
		function removeProject(id) {
			var defferred = $q.defer();
			$http.delete(urls.PROJECT_SERVICE_API+id).then(
					function(response) {
						loadAllProject();
						defferred.resolve(response.data);
					},
					function(errResponse){
						defferred.reject(errResponse);
					}
			);
			return defferred.promise;
		}
		
		
	}
]);