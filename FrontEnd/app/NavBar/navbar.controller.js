app.directive("navbar", function () {
    var directive = {
      restrict: "E",
      templateUrl: "app/NavBar/NavBar.html",
      scope: true,
    };
  
    return directive;
  });