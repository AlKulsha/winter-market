angular.module('market', []).controller('indexController', function($scope, $http){
     $scope.loadProducts = function(){
         $http.get('http://localhost:8189/market/api/v1/products/')
            .then(function(response){
                 $scope.productsList = response.data;
            });
     };

    $scope.deleteProductById = function(productId){
          $http.delete('http://localhost:8189/market/api/v1/products/' + productId)
              .then(function(response){
                 $scope.loadProducts();
              });
    };

    $scope.showProductInfo = function(productId){
        $http.get('http://localhost:8189/market/api/v1/products/' + productId)
            .then(function(response){
             alert(response.data.title);
        });
    };

    $scope.createNewProduct = function(){
        $http.post('http://localhost:8189/market/api/v1/products/', $scope.newProduct)
            .then(function(response){
                $scope.newProduct = null;
                $scope.loadProducts();
            });
    };




     $scope.loadCart = function(){
          $http.get('http://localhost:8189/market/api/v1/cart')
                 .then(function(response){
                      $scope.cart = response.data;
                 });
     };


    $scope.addProductToCart = function(productId){
         $http.get('http://localhost:8189/market/api/v1/cart/add/' + productId)
            .then(function(){
                $scope.loadCart();
         });
    };

     $scope.clearCart = function(){
             $http.get('http://localhost:8189/market/api/v1/cart/clear')
                .then(function(){
                    $scope.loadCart();
             });
     };

     $scope.removeFromCart = function(productId){
           $http.get('http://localhost:8189/market/api/v1/cart/remove/' + productId)
               .then(function(response){
                  $scope.loadCart();
               });
     };


    $scope.loadProducts();
    $scope.loadCart();

});


