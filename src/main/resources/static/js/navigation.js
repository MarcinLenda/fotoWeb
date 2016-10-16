/**
 * Created by Promar on 08.10.2016.
 */


app.controller('navigation',['$rootScope', '$http', '$location', '$route','$scope','fileUpload', function($rootScope, $http, $location, $route, $scope,fileUpload) {

        var self = this;
        $scope.errorForm = '';
        $scope.userInfo = 'test';
        $scope.roles = '';

        self.tab = function(route) {
            return $route.current && route === $route.current.controller;
        };



        var authenticated = function(credentials, callback) {

            var headers = credentials ? {
                authorization : "Basic "
                + btoa(credentials.username + ":"
                    + credentials.password)
            } : {};

            console.log("authenticated "+credentials);

            $http.get('/myAccount/user', {
                headers : headers
            }).then(function(response) {
                var data = response.data;
                if (data.name) {
                    self.authenticated = true;
                    self.user = data.name;
                    $scope.userInfo = data.name;
                    self.admin = data && data.roles && data.roles.indexOf("ROLE_ADMIN")>-1;
                    $scope.roles = data.roles;
                    console.log(data.roles);
                    console.log(data.name);
                } else {
                    self.authenticated = false;
                    self.admin = false;
                }
                callback && callback(true);
            }, function() {
                self.authenticated = false;
                callback && callback(false);
            });
        }

        authenticated();

        self.credentials = {};
        self.login = function() {

            console.log("Login");

            authenticated(self.credentials, function(authenticated) {
                if (authenticated) {
                    $location.path("/");
                    self.error = false;
                    $rootScope.authenticated = true;
                    $scope.userInfo = self.user.username;
                } else {
                    $scope.errorForm = "Błędne dane!";
                    $location.path("/login");
                    self.error = true;
                    $rootScope.authenticated = false;
                }
            })
        };

    $scope.logout = function() {
        $http.post('logout', {}).success(function() {
            $rootScope.authenticated = false;
            $location.path("/");
        }).error(function(data) {
            $rootScope.authenticated = false;
        });
    }

}]);
