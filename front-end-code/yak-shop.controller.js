var app = angular.module('myApp', []);
	app.controller('myCtrl', function($scope, $http) {
		$scope.click = function() {
			var url = "http://localhost:8080/yak-shop/order/14";
			$scope.nameMsg = null;
			$scope.orderMsg = null;
			$scope.skinMsg = null;
            $scope.sucess = false;
			var nameData = $scope.name;
			var milkData = $scope.milk;
			if(milkData == null || milkData == undefined){ milkData = 0;}
			var skinData = $scope.skin;
			if(skinData == null || skinData == undefined){ skinData = 0;}
			if(nameData == null) {
				$scope.nameMsg = "Plese enter your Name.";
				return;
			}
			if(milkData == 0 && skinData == 0) {
				$scope.orderMsg = "Plese order at least one out of Milk & Wool.";
				return;
			} else {
				var regEx = /^\d+$/;
				if(!regEx.test(skinData)) {
					$scope.skinMsg = "Skin count must be an integer.";
					return;
				}
			}
			
			var parameter = JSON.stringify({customer:nameData, order:{milk:milkData, skin:skinData}});
				$http.post(url, parameter,{headers: {'Content-Type': 'application/json'}}).
		    success(function(data) {
		        
		        $scope.orderProcessed = "Order processed: ";
		        $scope.orderResult = data;
		      }).
		      error(function(data) {
		      });
		}
	});