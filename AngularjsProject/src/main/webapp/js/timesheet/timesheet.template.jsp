<div class="container">

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">Specific Project </span>
		</div>
		<div class="panel-body">
			<div class="formcontainer">
				<div class="alert alert-success" role="alert"
					ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
				<div class="alert alert-danger" role="alert"
					ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
				<form ng-submit="ctrl.submit()" name="timesheetForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.timesheet.idTimesheet" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="uname">Personal</label>
							<div class="col-md-7">
								<select class="form-control" ng-model="ctrl.timesheet.personal.id" required="required">
									<option value="">----- Select personal -----</option>
									<option ng-repeat="p in ctrl.getAllPersonals()" ng-selected="ctrl.timesheet.personal.id == p.id" value="{{p.id}}">{{p.fullname}}</option>
								</select>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable">Select Project</label>
							<div class="col-md-7">
								<select class="form-control" ng-model="ctrl.timesheet.project.id" required="required">
									<option value="">----- Select project -----</option>
									<option ng-repeat="p in ctrl.getAllProjects()" value="{{p.id}}" ng-selected="ctrl.timesheet.project.id == p.id">{{p.name}}</option>
								</select>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable">Location</label>
							<div class="col-md-7">
								<input type="text" name="location" ng-model="ctrl.timesheet.location"
									class="form-control input-sm" required ng-minlength="3" ng-pattern="/^[a-zA-Z0-9_ ]*$/"/>
							</div>
							
							<div class="col-md-3">
								<i class="fa fa-check text-success"
									ng-show="timesheetForm.location.$dirty && timesheetForm.location.$valid">
								</i>
								<div
									ng-show="timesheetForm.location.$dirty && timesheetForm.location.$invalid"
									class="text-danger">
									<i class="fa fa-times text-danger"></i>
								</div>

								<span ng-show="timesheetForm.location.$error.minlength"
									class="error">- Location must be at least 3 characters</span><br/>

								<span ng-show="timesheetForm.location.$error.pattern"
									class="error">- Location is only text</span>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable">Work day</label>
							<div class="col-md-7">
								<input type="number" ng-model="ctrl.timesheet.workday"
									class="form-control input-sm" required />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit"
								value="{{!ctrl.timesheet.idTimesheet ? 'Add' : 'Update'}}"
								class="btn btn-primary btn-sm"
								ng-disabled="timesheetForm.$invalid || timesheetForm.$pristine">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm"
								ng-disabled="timesheetForm.$pristine">Reset Form</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<hr />
	<h2>List of Project</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Location</th>
				<th>Work Day</th>
				<th>Personal</th>
				<th>Project</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="p in ctrl.getAllTimesheets()">
				<td>{{p.location}}</td>
				<td>{{p.workday}}</td>
				<td>{{p.personal.fullname}}</td>
				<td>{{p.project.name}}</td>
				<td><button type="button" ng-click="ctrl.timesheetToSubmit(p.idTimesheet)"
						class="btn btn-success custom-width">Edit</button></td>
				<td><button type="button" ng-click="ctrl.confirm(p.idTimesheet)"
						class="btn btn-danger custom-width">Remove</button></td>
			</tr>
		</tbody>
	</table>
</div>