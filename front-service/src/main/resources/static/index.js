angular.module('market', ['ngStorage']).controller('indexController', function($scope, $http, $localStorage){

    $scope.tryToAuth = function(){
             $http.post('http://localhost:5555/auth/auth', $scope.user)
                .then(function successCallback(response){
                    if(response.data.token){
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                        $scope.user.username = null;
                        $scope.user.password = null;
                    }
                }, function errorCallback(response){
                });
    };

    $scope.tryToLogout = function(){
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function(){
        delete $localStorage.winterMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function(){
        if($localStorage.winterMarketUser){
            return true;
        } else {
            return false;
        }
    };

//*************************************************

     $scope.loadProducts = function(){
         $http.get('http://localhost:5555/core/api/v1/products')
            .then(function(response){
                 $scope.productsList = response.data;
            });
     };

    $scope.createOrder = function(){
          $http.post('http://localhost:5555/core/api/v1/orders')
             .then(function(response){
                  alert('The order was created');
                  $scope.loadCart();
             });
      };

    $scope.deleteProductById = function(productId){
          $http.delete('http://localhost:5555/core/api/v1/products/' + productId)
              .then(function(response){
                 $scope.loadProducts();
              });
    };

    $scope.showProductInfo = function(productId){
        $http.get('http://localhost:5555/core/api/v1/products/' + productId)
            .then(function(response){
             alert(response.data.title);
        });
    };

    $scope.createNewProduct = function(){
        $http.post('http://localhost:5555/core/api/v1/products/', $scope.newProduct)
            .then(function(response){
                $scope.newProduct = null;
                $scope.loadProducts();
            });
    };


//************************************************************************

     $scope.loadCart = function(){
          $http.get('http://localhost:5555/cart/api/v1/cart')
                 .then(function(response){
                      $scope.cart = response.data;
                 });
     };

    $scope.addProductToCart = function(productId){
         $http.get('http://localhost:5555/cart/api/v1/cart/add/' + productId)
            .then(function(){
                $scope.loadCart();
         });
    };

     $scope.clearCart = function(){
             $http.get('http://localhost:5555/cart/api/v1/cart/clear')
                .then(function(){
                    $scope.loadCart();
             });
     };

     $scope.removeFromCart = function(productId){
           $http.get('http://localhost:5555/cart/api/v1/cart/remove/' + productId)
               .then(function(response){
                  $scope.loadCart();
               });
     };


    $scope.loadProducts();
    $scope.loadCart();

});


