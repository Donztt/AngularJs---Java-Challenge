app.controller(
    "AddProdutoController",
    function ($rootScope, $timeout, $scope, $http, produtoFactory) {
  
      $scope.showPopup = function () {
        $scope.popupVisible = true;
      };
      $scope.hidePopup = function () {
        $scope.popupVisible = false;
      };
      $scope.saveProduto = function () {
  
        if($scope.name == undefined){
          showAlert("Digite um nome para o produto");
          return;
        }
        if($scope.price <= 0){
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
        });
      };
    }
  );

  app.directive("addProduto", function () {
    var directive = {
      restrict: "E",
      templateUrl: "app/templates/addProduto.html",
      scope: true,
    };
  
    return directive;
  });
  