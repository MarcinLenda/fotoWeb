/**
 * Created by Promar on 16.10.2016.
 */



app.controller('registerCtrl',function($scope, $http) {

    $scope.mail = {};
    $scope.info= "";
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

                $scope.error="";
                $scope.info= "Wiadomość została wysłana";

            }).error(function (data) {
            $scope.info = "";
            $scope.error = "Błąd wysyłania wiadomości!";
        });
    }
});
