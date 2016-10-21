/**
 * Created by Promar on 08.10.2016.
 */


app.controller('navigation',['$rootScope', '$http', '$location', '$route','$scope','$window','fileUpload','service', function($rootScope, $http, $location, $route, $scope,$window,fileUpload, service) {

    var self = this;
    $scope.errorForm = '';
    $scope.showUserName = service.showUserName;



    self.tab = function(route) {
        return $route.current && route === $route.current.controller;
    };

     var authenticated = function (credentials, callback) {
         service.authenticated(credentials,callback);
    };

    self.credentials = {};

    self.login = function() {

        authenticated(self.credentials, function(authenticated) {
            if (authenticated) {
                $location.path('/#login')
                self.error = false;
                $rootScope.authenticated = true;
               // $scope.userInfo = self.user.username;
            } else {
                $scope.errorForm = "Błędne dane!";
                $location.path("/login");
                self.error = true;
                $rootScope.authenticated = false;
            }
        })
    };

    $scope.logout = function() {

        $http.post('/logout', {
        }).success(function() {
            $location.path("/login");
            $rootScope.authenticated = false;
            $rootScope.userRoles = false;

            $rootScope.info = "Zostałeś porawnie wylogowany!";

        }).error(function(data) {
            $rootScope.authenticated = false;
            $rootScope.userRoles = false;
            $rootScope.userInfo=false;
        });
    }
}]);
