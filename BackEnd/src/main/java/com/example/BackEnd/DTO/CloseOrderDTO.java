package com.example.BackEnd.DTO;

import com.example.BackEnd.models.Pedido;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CloseOrderDTO {
    private PedidoDTO pedidoDto;
    private float paymentValue;
    private String resultValue;
}
