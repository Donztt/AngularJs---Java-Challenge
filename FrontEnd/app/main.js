var app = angular.module("App", ["ngRoute"]);

app.constant('SERVER_PATH', 'http://localhost:8080');

app.config(function($routeProvider) {
  $routeProvider
    .when('/', {
      templateUrl: 'app/Pagination/pagination.html',
      controller: 'PaginationController'
    })
    .otherwise({
      redirectTo: '/'
    });
});


