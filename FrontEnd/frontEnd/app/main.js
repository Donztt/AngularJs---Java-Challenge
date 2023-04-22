var app = angular.module("todoApp", []);
app.controller(
  "TodoController",
  function ($rootScope, $timeout, $scope, $http) {
    $scope.serverPath = "http://localhost:8080";
    $scope.todos = [];

    getTodos($rootScope, $timeout, $scope, $http, paginationFilter);

    $scope.$on("getTodos", function (evt) {
      getTodos($rootScope, $timeout, $scope, $http, paginationFilter);
    });
    $scope.changeSorting = function (sortBy) {
      paginationFilter = {
        ...paginationFilter,
        sortBy: sortBy,
        descending: !paginationFilter.descending,
      };
      $scope.$broadcast("getTodos");
    };

    $scope.closeOrder = function () {
      let data = {
        pedidoDto: $scope.selectedItem,
        paymentValue: $scope.paymentValue,
      };

      $http({
        method: "POST",
        url: $scope.serverPath + "/pedido/ClosePedido",
        data: data,
      }).then((response) => {
        $scope.hidePopup();
        showFinishOrder("Pagamento Realizado com sucesso! Valor do troco: " +  response.data.resultValue);
        $scope.paymentValue = "";
        $scope.$broadcast("getTodos");
      }).catch((response) => {
        showAlert(response.data);
      });
    };

    $scope.showPopup = function (item) {
      $scope.selectedItem = item;
      $scope.popupVisible = true;
    };
    $scope.hidePopup = function () {
      $scope.popupVisible = false;
    };
  }
);

app.controller(
  "AddProdutoController",
  function ($rootScope, $timeout, $scope, $http) {

    $scope.price = 1;

    $scope.showPopup = function () {
      $scope.popupVisible = true;
    };
    $scope.hidePopup = function () {
      $scope.popupVisible = false;
    };
    $scope.saveProduto = function () {

      if($scope.name == undefined){
        showAlert("Selecione um nome para o produto");
        return;
      }
      if($scope.price <= 0){
        showAlert("Selecione um a valor válido para do produto");
        return;
      }

      let data = {
        nomeProduto: $scope.name,
        preco: $scope.price,
      };

      $http({
        method: "POST",
        url: $scope.serverPath + "/produto/AddProduto",
        data: data,
      }).then((response) => {
        $scope.hidePopup();
        $scope.price = "";
        $scope.name = "";
      });
    };
  }
);

app.controller(
  "AddPedidoController",
  function ($rootScope, $timeout, $scope, $http) {
    $http({
      method: "GET",
      url: $scope.serverPath + "/produto/ListProdutos",
    }).then((response) => {
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

      $http({
        method: "POST",
        url: $scope.serverPath + "/pedido/AddPedido",
        data: data,
      }).then((response) => {
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

app.controller("PaginationController", function ($rootScope, $scope) {
  $scope.currentPage = paginationFilter.pageNo;

  $scope.pageSize = [
    {
      id: 1,
      name: "10",
    },
    {
      id: 2,
      name: "20",
    },
    {
      id: 3,
      name: "30",
    },
    {
      id: 4,
      name: "all",
    },
  ];

  $scope.$on("setPagination", function (evt, args) {
    $scope.currentPage = args.pageNo;
    $scope.totalItems = args.totalItems;
    $scope.totalPages = args.totalPages;
  });

  $scope.nextPage = function () {
    if ($scope.totalPages == paginationFilter.pageNo) {
      return;
    }
    paginationFilter = {
      ...paginationFilter,
      pageNo: paginationFilter.pageNo + 1,
    };
    $rootScope.$broadcast("getTodos");
  };
  $scope.previousPage = function () {
    if (paginationFilter.pageNo == 1) {
      return;
    }
    paginationFilter = {
      ...paginationFilter,
      pageNo: paginationFilter.pageNo - 1,
    };
    $rootScope.$broadcast("getTodos");
  };
  $scope.selectPage = function (page) {
    paginationFilter = {
      ...paginationFilter,
      pageNo: page,
    };
    $rootScope.$broadcast("getTodos");
  };
  $scope.lastPage = function () {
    paginationFilter = {
      ...paginationFilter,
      pageNo: $scope.totalPages,
    };
    $rootScope.$broadcast("getTodos");
  };

  $scope.getSelectValue = function () {
    paginationFilter = {
      ...paginationFilter,
      pageSize: $scope.selected.name,
    };
    $rootScope.$broadcast("getTodos");
  };
});

var paginationFilter = {
  sortBy: "createdDate",
  pageNo: 1,
  pageSize: 20,
  descending: true,
};

function showAlert(message) {
  var alertDiv = document.createElement('div');
  alertDiv.innerHTML = message;
  alertDiv.style.padding = '10px';
  alertDiv.style.borderRadius = '5px';
  alertDiv.style.backgroundColor = 'red';
  alertDiv.style.color = 'black';
  alertDiv.style.display = 'flex';
  alertDiv.style.alignItems = 'center';

  setTimeout(function() {
    alertDiv.style.display = "none";
  }, 5000);

  document.body.prepend(alertDiv);
}

function showFinishOrder(message) {
  var alertDiv = document.createElement('div');
  alertDiv.innerHTML = message;
  alertDiv.style.padding = '10px';
  alertDiv.style.borderRadius = '5px';
  alertDiv.style.backgroundColor = 'green';
  alertDiv.style.color = 'black';
  alertDiv.style.display = 'flex';
  alertDiv.style.alignItems = 'center';

  setTimeout(function() {
    alertDiv.style.display = "none";
  }, 8000);

  document.body.prepend(alertDiv);
}

function getTodos($rootScope, $timeout, $scope, $http, filter) {
  //$scope.loading = true;
  var data = {
    sortBy: filter.sortBy,
    pageNo: filter.pageNo,
    pageSize: filter.pageSize,
    descending: filter.descending,
  };
  data = JSON.stringify(data);

  $http({
    method: "POST",
    url: $scope.serverPath + "/pedido/PaginationPedidos",
    data: data,
  }).then((response) => {
    $timeout(
      function () {
        if (!$scope.$root.$$phase) {
          $scope.$apply(function () {
            $scope.todos = response.data.content;

            $rootScope.$broadcast("setPagination", {
              pageNo: paginationFilter.pageNo,
              totalItems: response.data.totalElements,
              totalPages: response.data.totalPages,
            });
          });
        }
      },
      500,
      true
    );
  });
}

app.directive("pedidoPagination", function () {
  var directive = {
    restrict: "E",
    templateUrl: "app/templates/pedidoPagination.html",
    scope: true,
  };

  return directive;
});

app.directive("addProduto", function () {
  var directive = {
    restrict: "E",
    templateUrl: "app/templates/addProduto.html",
    scope: true,
  };

  return directive;
});

app.directive("addPedido", function () {
  var directive = {
    restrict: "E",
    templateUrl: "app/templates/addPedido.html",
    scope: true,
  };

  return directive;
});

app.directive("pagination", function () {
  var directive = {
    restrict: "E",
    templateUrl: "app/templates/pagination.html",
    scope: true,
  };

  return directive;
});

app.directive("navbar", function () {
  var directive = {
    restrict: "E",
    templateUrl: "app/templates/NavBar.html",
    scope: true,
  };

  return directive;
});
