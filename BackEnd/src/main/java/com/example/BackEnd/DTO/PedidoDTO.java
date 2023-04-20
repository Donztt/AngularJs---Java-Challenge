package com.example.BackEnd.DTO;

import com.example.BackEnd.models.Pedido;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoDTO {

    private Long id;
    private Long idProduto;
    private int qtdProduto;

    public Pedido toModel(){
        Pedido pedido = new Pedido();
        pedido.setId(this.id);
        pedido.setIdProduto(this.idProduto);
        pedido.setQtdProduto(this.qtdProduto);

        return pedido;
    }
}
