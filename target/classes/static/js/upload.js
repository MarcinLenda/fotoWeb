/**
 * Created by Promar on 30.09.2016.
 */
app .directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);


app.controller('myCtrlUpload', ['$scope','$http', 'fileUpload', function($scope, $http, fileUpload){

    $scope.myFile ="";
    $scope.names = ["Opuszczone miejsca", "Portret i modeling", "Piłka nożna",
        "Krajobrazy i miasta"];

    $scope.size = "1";

    console.log('Sciezka zdjecia'+fileUpload.getur);

    $scope.uploadFile = function(){

        var nameAlbum='';

        if($scope.album=="Opuszczone miejsca"){
            nameAlbum = 'album1';
        }else if($scope.album=="Portret i modeling"){
            nameAlbum = 'album2';
        }else if($scope.album=="Piłka nożna"){
            nameAlbum = 'album3';
        }else{
            nameAlbum = 'album4';
        }

        var file = $scope.myFile;
        var description = $scope.description;




        // console.log($scope.album);
        console.dir(file);
        fileUpload.uploadFileToUrl(file, nameAlbum, description);
    };
}]);