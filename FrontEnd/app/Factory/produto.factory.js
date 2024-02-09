app.factory("produtoFactory", function ($http, SERVER_PATH) {
  return {
    addProduto: function (params) {
      return $http({
        method: "POST",
        url: SERVER_PATH + "/produto/AddProduto",
        data: params,
      });
    },
    deleteProduto: function (id) {
      return $http({
        method: "DELETE",
        url: SERVER_PATH + "/produto/DeleteProduto/"+ id,
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
