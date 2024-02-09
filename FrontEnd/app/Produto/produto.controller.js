app.controller(
  "ProdutoController",
  function ($rootScope, $timeout, $scope, $http, produtoFactory) {
    $scope.qtdProduct = 1;

    $scope.$on("getAllProdutos", function (evt) {
      produtoFactory.getAllProdutos().then((response) => {
        console.log(response.data);
        $timeout(function() {
          $scope.products = response.data;
        });
      });
    });

    $scope.$broadcast("getAllProdutos");

    $scope.showPopup = function () {
      $scope.popupVisible = true;
    };
    $scope.hidePopup = function () {
      $scope.popupVisible = false;
    };
    $scope.saveProduto = function () {
      if ($scope.name == undefined) {
        showAlert("Digite um nome para o produto");
        return;
      }
      if ($scope.price <= 0) {
        showAlert("Digite um a valor válido para o produto");
        return;
      }

      let data = {
        nomeProduto: $scope.name,
        preco: $scope.price,
      };

      produtoFactory.addProduto(data).then((response) => {
        $scope.hidePopup();
        $scope.price = "";
        $scope.name = "";
        $scope.$broadcast("getAllProdutos");
      });
    };

    $scope.deletarProduto = function () {
      if ($scope.selectedProduct.id == undefined) {
        showAlert("Selecione um produto para o deletar");
        return;
      }

      produtoFactory
        .deleteProduto($scope.selectedProduct.id)
        .then((response) => {
          $scope.hidePopup();
          $scope.$broadcast("getAllProdutos");
          $scope.$broadcast("getTodos");
        });
    };
  }
);
