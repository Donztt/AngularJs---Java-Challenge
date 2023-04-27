app.factory("produtoFactory", function ($http, SERVER_PATH) {
  return {
    addProduto: function (params) {
      return $http({
        method: "POST",
        url: SERVER_PATH + "/produto/AddProduto",
        data: params,
      });
    },
    getAllProdutos: function (params) {
      return $http({
        method: "GET",
        url: SERVER_PATH + "/produto/ListProdutos",
      });
    },
  };
});
