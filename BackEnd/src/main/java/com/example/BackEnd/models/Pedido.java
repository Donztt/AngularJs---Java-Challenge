package com.example.BackEnd.models;

import com.example.BackEnd.DTO.PedidoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Pedido")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long idProduto;
    private int qtdProduto;

    public PedidoDTO toDTO(){
        PedidoDTO pedidoDto = new PedidoDTO();
        pedidoDto.setId(this.id);
        pedidoDto.setIdProduto(this.idProduto);
        pedidoDto.setQtdProduto(this.qtdProduto);

        return pedidoDto;
    }
}
