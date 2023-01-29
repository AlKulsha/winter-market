angular.module('market').controller('cartController', function ($scope, $http, $localStorage) {

    $scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.winterMarketGuestCartId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

     $scope.clearCart = function(){
             $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.winterMarketGuestCartId + '/clear')
                .then(function(){
                    $scope.loadCart();
             });
     };

     $scope.removeFromCart = function(productId){
           $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.winterMarketGuestCartId + '/remove/' + productId)
               .then(function(response){
                  $scope.loadCart();
               });
     };

    $scope.createOrder = function(){
          $http.post('http://localhost:5555/core/api/v1/orders')
             .then(function(response){
                  alert('The order was created');
                  $scope.loadCart();
             });
    };


    $scope.guestCreateOrder = function () {
        alert('Для оформления заказа необходимо войти в учетную запись');
    }

    $scope.loadCart();
});