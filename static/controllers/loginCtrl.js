(function() {
	// controller bound to application body, parent controller to all others
	angular.module("myApp").controller('loginController', loginController);
	
	function loginController($scope, $http, $window, toastr) {
		var vm = this;
		
		vm.login = function(){
			
			var userData =  { "username": vm.username, "password": vm.password };
	        
			$http.post('/api/users/login', userData).then(function(response) {
				
				$scope.indexCtrl.login(response.data.response);
				toastr.success(vm.username + " welcome!");
				$window.location = "#!/home";

			}, function(response) {
				toastr.error(response.data.response);
			});
		}
	}
    
})();