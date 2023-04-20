package com.example.BackEnd.models;

import com.example.BackEnd.DTO.ProdutoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Produto")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;
    private float preco;

    public ProdutoDTO toDTO(){
        ProdutoDTO produtoDto = new ProdutoDTO();
        produtoDto.setId(this.id);
        produtoDto.setNomeProduto(this.nomeProduto);
        produtoDto.setPreco(this.preco);

        return produtoDto;
    }
}
