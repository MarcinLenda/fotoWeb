/**
 * Created by Promar on 16.10.2016.
 */



app.controller('registerCtrl',function($scope, $http) {

    $scope.registerResponse = {};
    //$scope.info= "";
    $scope.error= "";
    $scope.registerUser = function () {
        console.log('insta');
        $http({
            method  : 'POST',
            url     : 'http://localhost:8080/myAccount/register',
            data    : {
                "username": $scope.register.username,
                "password": $scope.register.password,
                "email": $scope.register.email
            },
            headers : {'Content-type': 'application/json'}
        })
            .success(function (data) {
                $scope.registerResponse = data;

                console.log("+"+registerResponse);

            }).error(function (data) {

            $scope.registerResponse = data;

        });
    }
});
