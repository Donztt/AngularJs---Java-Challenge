package com.example.BackEnd.DTO;

import com.example.BackEnd.models.Pedido;
import com.example.BackEnd.models.Produto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoDTO {

    private Long id;
    private String nomeProduto;
    private float preco;

    public Produto toModel(){
        Produto produto = new Produto();
        produto.setId(this.id);
        produto.setNomeProduto(this.nomeProduto);
        produto.setPreco(this.preco);

        return produto;
    }
}
