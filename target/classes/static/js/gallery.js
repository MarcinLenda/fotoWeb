
app.controller('albumCtrl', function($scope, $http, $window) {


  $scope.deleteInfo = "";
  $scope.deleteError = "Nie udało się usunąć zdjęcia!";
  $scope.images = {};
  $scope.selected ={};





  $scope.deletePhoto = function() {

    $scope.images = $.grep($scope.images, function( image ) {
      return $scope.selected[ image.id ];
    });
    $http({
      headers : {'Content-type': 'application/json'},
      method  : 'DELETE',
      url     : 'http://52.39.52.69:8080/photo/deletePhoto',
      data    : $scope.selected,

    })
        .success(function (data) {
          $scope.deleteInfo = "Zdjęcie zostało usunięte!";
          $window.location.href = '#/info';




        }).error(function (data) {
          $window.location.href = '#/info';
    });
  };

  $http({
    method  : 'GET',
    url     : 'http://52.39.52.69:8080/photo/allPhotos',
    headers : {'Content-type': 'application/json'}
  })
      .success(function (data) {
        $scope.images = data;
      }).error(function (data) {

    $scope.images = 'Nie mogę załadować zdjęcia!';
  });


});