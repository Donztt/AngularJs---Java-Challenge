app.factory('pedidoFactory', function ($http, SERVER_PATH) {
    return {
        getAllPedidos: function(params) {
            return $http({
                method: "POST",
                url: SERVER_PATH + "/pedido/PaginationPedidos",
                data: params,
              })
        },
        addPedido: function(params) {
            return $http({
                method: "POST",
                url: SERVER_PATH + "/pedido/AddPedido",
                data: params,
              })
        },
        closeOrder: function(params) {
            return $http({
                method: "POST",
                url: SERVER_PATH + "/pedido/ClosePedido",
                data: params,
              })
        }
    }
  })