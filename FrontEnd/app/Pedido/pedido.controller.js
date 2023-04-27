app.controller(
    "AddPedidoController",
    function ($rootScope, $timeout, $scope, $http, pedidoFactory, produtoFactory) {
      produtoFactory.getAllProdutos().then((response) => {
        $scope.products = response.data;
      });
      
      $scope.qtdProduct = 1;
  
      $scope.savePedido = function () {
        if($scope.selectedProduct.id == undefined){
          showAlert("Selecione um produto para o pedido");
          return;
        }
        if($scope.qtdProduct <= 0){
          showAlert("Selecione um a quantidade válida para do pedido");
          return;
        }
  
        var data = {
          idProduto: $scope.selectedProduct.id,
          qtdProduto: $scope.qtdProduct,
        }
  
        pedidoFactory.addPedido(data).then((response) => {
          $scope.hidePopup();
          $scope.price = "";
          $scope.name = "";
          $scope.$broadcast("getTodos");
        });
      }
      $scope.showPopup = function () {
        $scope.popupVisible = true;
      };
      $scope.hidePopup = function () {
        $scope.popupVisible = false;
      };
    }
  );