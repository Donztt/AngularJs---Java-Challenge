app.controller(
  "PaginationController",
  function ($rootScope, $timeout, $scope, $http, pedidoFactory) {
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

    $scope.todos = [];

    $scope.$on("getTodos", function (evt) {
      var data = {
        sortBy: paginationFilter.sortBy,
        pageNo: paginationFilter.pageNo,
        pageSize: paginationFilter.pageSize,
        descending: paginationFilter.descending,
      };
      data = JSON.stringify(data);

      pedidoFactory.getAllPedidos(data).then((response) => {
        $timeout(function () {
          $scope.todos = response.data.content;

          $rootScope.$broadcast("setPagination", {
            pageNo: paginationFilter.pageNo,
            totalItems: response.data.numberOfElements,
            totalPages: response.data.totalPages,
          });
        });
      });
    });

    $scope.$broadcast("getTodos");

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

      pedidoFactory
        .closeOrder(data)
        .then((response) => {
          $scope.hidePopup();
          showFinishOrder(
            "Pagamento Realizado com sucesso! Valor do troco: " +
              response.data.resultValue
          );
          $scope.paymentValue = "";
          $scope.$broadcast("getTodos");
        })
        .catch((response) => {
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
  }
);

var paginationFilter = {
  sortBy: "createdDate",
  pageNo: 1,
  pageSize: 20,
  descending: true,
};
