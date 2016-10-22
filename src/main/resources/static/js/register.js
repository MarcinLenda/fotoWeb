/**
 * Created by Promar on 16.10.2016.
 */



app.controller('registerCtrl',function($scope, $http, $location) {

    $scope.registerResponse = {};


    $scope.error= "";
    $scope.registerUser = function () {
        console.log('insta');
        $http({
            method  : 'POST',
            url     : 'http://localhost:8080/myAccount/register',
            data    :
            {
                "username": $scope.register.username,
                "password": $scope.register.password,
                "confirmPassword": $scope.register.confirmPassword,
                "email": $scope.register.email
            },
            headers : {'Content-type': 'application/json'}
        })
            .success(function (data) {
                $location.path("/info");

            }).error(function (data) {
                $scope.registerResponse = data.Error;

        });
    }
});
