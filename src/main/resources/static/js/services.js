/**
 * Created by Promar on 17.10.2016.
 */

app.service('service', function ($rootScope, $http, $location ) {

    var self = this;
    var _username = '';
    $rootScope.userRoles = false;
    $rootScope.logout = false;

   var setUsername = function (username) {
        _username = username;
    };

    this.showUserName = function () {
        return "Jesteś zalogowany jako: "+_username;
    };


    $http({
        method: 'GET',
        url: '/myAccount/user'
    }).then(function successCallback(response) {
        var data = response.data;
        $rootScope.authenticated = true;
        setUsername(data.name);
        if(data.name=='admin'){
            $rootScope.userRoles = true;
        }

    }, function errorCallback(response) {
        $rootScope.authenticated = false;
        console.log('authenticated false');
    });

        this.authenticated = function(credentials, callback) {

        var headers = credentials ? {
            authorization : "Basic "
            + btoa(credentials.username + ":"
                + credentials.password)
        } : {};


        $http.get('/myAccount/user', {
            headers : headers
        }).then(function(response) {
            var data = response.data;
            if (data.name) {
                $rootScope.authenticated = true;
                setUsername(data.name);
                $rootScope.admin = data && data.roles && data.roles.indexOf("ROLE_ADMIN")>-1;

                if(data.name=='admin'){
                    $rootScope.userRoles = true;
                }

            } else {
                self.authenticated = false;
                self.admin = false;
            }
            callback && callback(true);
        }, function() {
            self.authenticated = false;
            callback && callback(false);
        });
    };

    self.credentials = {};


});

app.service('fileUpload', ['$http', function ($http) {
    var photoInfo = "";
    var photoError = "";

    this.uploadFileToUrl = function(file, nameAlbum, description){
        var fd = new FormData();

        fd.append('file', file);
        fd.append('name', nameAlbum );
        fd.append('descritpion',description );

        $http.post("http://localhost:8080/photo/upload", fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined},
            data: fd
        })
            .success(function(){
                photoInfo = "Zdjęcie dodano do albumu:"+nameAlbum;
            })
            .error(function(){
                photoError = "Nie udało się dodać zdjęcia";
            });
    };
}]);