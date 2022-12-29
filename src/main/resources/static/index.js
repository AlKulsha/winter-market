angular.module('market', []).controller('indexController', function($scope, $http){
     $scope.loadProducts = function(){
         $http.get('http://localhost:8189/market/api/v1/products/')
            .then(function(response){
                 $scope.productsList = response.data;
            });
     };

     $scope.showCart = function(){
          $http.get('http://localhost:8189/market/api/v1/carts')
                 .then(function(response){
                      $scope.productsInCartList = response.data;
                 });
     };

    $scope.showProductInfo = function(productId){
        $http.get('http://localhost:8189/market/api/v1/products/' + productId)
            .then(function(response){
             alert(response.data.title);
        });
    };

    $scope.addProductToCart = function(productId){
         $http.get('http://localhost:8189/market/api/v1/carts/add/' + productId)
            .then(function(response){
                $scope.showCart();
         });
    };

    $scope.deleteProductById = function(productId){
          $http.delete('http://localhost:8189/market/api/v1/products/' + productId)
              .then(function(response){
                 $scope.loadProducts();
              });
    };

    $scope.createNewProduct = function(){
        $http.post('http://localhost:8189/market/api/v1/products/', $scope.newProduct)
            .then(function(response){
                $scope.newProduct = null;
                $scope.loadProducts();
            });
    };

    $scope.loadProducts();

});


