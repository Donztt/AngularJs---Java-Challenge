package com.example.BackEnd.DTO;

import com.example.BackEnd.models.Pedido;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PedidoDTO {

    private Long id;
    private Long idProduto;
    private int qtdProduto;
    private String produtoName;
    private Date createdDate;

    public Pedido toModel(){
        Pedido pedido = new Pedido();
        pedido.setId(this.id);
        pedido.setIdProduto(this.idProduto);
        pedido.setQtdProduto(this.qtdProduto);
        pedido.setCreatedDate(this.createdDate);

        return pedido;
    }
}
