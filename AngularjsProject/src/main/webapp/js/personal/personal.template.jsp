<div class="container">

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">Specific Personal </span>
		</div>
		<div class="panel-body">
			<div class="formcontainer">
				<div class="alert alert-success" role="alert"
					ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
				<div class="alert alert-danger" role="alert"
					ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
				<form ng-submit="ctrl.submit()" name="personalForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.personal.id" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="uname">Address</label>
							<div class="col-md-7">
								<input type="text" name="address" ng-model="ctrl.personal.address" id="uname"
									class="username form-control input-sm"
									placeholder="Enter your address" required ng-minlength="5" />
							</div>
							
							<div class="col-md-3">
								<i class="fa fa-check text-success"
									ng-show="personalForm.address.$dirty && personalForm.address.$valid">
								</i>
								<div
									ng-show="personalForm.address.$dirty && personalForm.address.$invalid"
									class="text-danger">
									<i class="fa fa-times text-danger"></i>
								</div>

								<span ng-show="personalForm.address.$error.minlength"
									class="error">Address must be at least 5 characters</span>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="age">Date of
								Birth</label>
							<div class="col-md-7">
								<input type="date" ng-model="ctrl.personal.dob" id="dob"
									class="form-control input-sm" required />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="name">Fullname</label>
							<div class="col-md-7">
								<input type="text" name="fullname"
									ng-model="ctrl.personal.fullname" id="name"
									class="form-control input-sm" placeholder="Enter your Fullname"
									required ng-minlength="5" ng-pattern="ctrl.onlyText" />
							</div>

							<div class="col-md-3">
								<i class="fa fa-check text-success"
									ng-show="personalForm.fullname.$dirty && personalForm.fullname.$valid">
								</i>
								<div
									ng-show="personalForm.fullname.$dirty && personalForm.fullname.$invalid"
									class="text-danger">
									<i class="fa fa-times text-danger"></i>
								</div>

								<span ng-show="personalForm.fullname.$error.pattern"
									class="error">Fullname is only text</span>
							</div>
						</div>

					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="gender">Gender</label>
							<div class="col-md-7">
								<input type="radio" ng-model="ctrl.personal.gender" id="gender"
									value="Male" />Male <input type="radio"
									ng-model="ctrl.personal.gender" id="gender" value="Female" />Female
							</div>
							
							
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="phone">Phone</label>
							<div class="col-md-7">
								<input type="text" name="phone" ng-model="ctrl.personal.phone" id="phone"
									class="form-control input-sm"
									placeholder="Enter your phone number" required ng-pattern="/^(\+84|0)\d{9,10}$/"/>
							</div>
							<div class="col-md-3">
								<i class="fa fa-check text-success"
									ng-show="personalForm.phone.$dirty && personalForm.phone.$valid">
								</i>
								<div
									ng-show="personalForm.phone.$dirty && personalForm.phone.$invalid"
									class="text-danger">
									<i class="fa fa-times text-danger"></i>
								</div>

								<span ng-show="personalForm.phone.$error.pattern"
									class="error">Phone number is invalid</span>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit"
								value="{{!ctrl.personal.id ? 'Add' : 'Update'}}"
								class="btn btn-primary btn-sm"
								ng-disabled="personalForm.$invalid || personalForm.$pristine">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm"
								ng-disabled="personalForm.$pristine">Reset Form</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<hr />
	<h2>List of Person</h2>
	<form class="form-inline">
		<div class="form-group">
			<label>Search</label> <input type="text" ng-model="search"
				class="form-control" placeholder="Search">
		</div>
	</form>
	<table class="table table-hover">
		<thead>
			<tr>
				<th ng-click="sort('id')">ID <span class="glyphicon sort-icon"
					ng-show="sortKey=='id'"
					ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
				</th>

				<th ng-click="sort('address')">Address <span
					class="glyphicon sort-icon" ng-show="sortKey=='address'"
					ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
				</th>
				<th ng-click="sort('dob')">Date Of Birth <span
					class="glyphicon sort-icon" ng-show="sortKey=='dob'"
					ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
				</th>
				<th ng-click="sort('fullname')">Fullname <span
					class="glyphicon sort-icon" ng-show="sortKey=='fullname'"
					ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
				</th>
				<th ng-click="sort('gender')">Gender <span
					class="glyphicon sort-icon" ng-show="sortKey=='gender'"
					ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
				</th>
				<th>Phone</th>
			</tr>
		</thead>
		<tbody>
			<tr
				ng-repeat="p in ctrl.getAllPersonals() | orderBy:sortKey:reverse | filter:search">
				<td>{{p.id}}</td>
				<td>{{p.address}}</td>
				<td>{{p.dob | date: "dd/MM/yyyy"}}</td>
				<td>{{p.fullname}}</td>
				<td>{{p.gender}}</td>
				<td>{{p.phone}}</td>
				<td><button type="button"
						ng-click="ctrl.personalToSubmit(p.id)"
						class="btn btn-success custom-width">Edit</button></td>
				<td><button type="button" ng-click="ctrl.confirm(p.id)"
						class="btn btn-danger custom-width">Remove</button></td>
			</tr>
		</tbody>

	</table>
	<!-- 	<dir-pagination-controls -->
	<!-- 					max-size="2" -->
	<!-- 					direction-links="true" -->
	<!-- 					boundary-links="true"> -->
	<!-- 	</dir-pagination-controls> -->
</div>