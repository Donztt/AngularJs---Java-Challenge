var app = angular.module("App", ["ngRoute"]);

app.constant('SERVER_PATH', 'http://localhost:8080');

app.config(function($routeProvider, $locationProvider) {
  $locationProvider.html5Mode(true)
  
  $routeProvider
  .when('/', {
    restrict: "E",
    templateUrl: 'app/Pagination/pagination.html',
    controller: 'PaginationController'
  })
  .otherwise({
    redirectTo: '/'
  });
});

app.directive("pedidoPagination", function () {
  var directive = {
    restrict: "E",
    templateUrl: "app/Pagination/pedidoPagination.html",
    scope: true,
  };

  return directive;
});

app.directive("pagination", function () {
  var directive = {
    restrict: "E",
    templateUrl: "app/Pagination/pagination.html",
    scope: true,
  };

  return directive;
});

app.directive("addPedido", function () {
  var directive = {
    restrict: "E",
    templateUrl: "app/Pedido/addPedido.html",
    scope: true,
  };

  return directive;
});

app.directive("navbar", function () {
  var directive = {
    restrict: "E",
    templateUrl: "app/NavBar/NavBar.html",
    scope: true,
  };

  return directive;
});

app.directive("addProduto", function () {
  var directive = {
    restrict: "E",
    templateUrl: "app/Produto/addProduto.html",
    scope: true,
  };

  return directive;
});

