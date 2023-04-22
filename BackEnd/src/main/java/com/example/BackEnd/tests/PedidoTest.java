package com.example.BackEnd.tests;

import com.example.BackEnd.DTO.CloseOrderDTO;
import com.example.BackEnd.DTO.PaginationRequestDTO;
import com.example.BackEnd.DTO.PedidoDTO;
import com.example.BackEnd.models.Produto;
import com.example.BackEnd.services.PedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PedidoTest {
    @Autowired
    private PedidoService _pedidoService;
    @Test
    public void addPedido() {
        var pedidoDto = new PedidoDTO();
        pedidoDto.setId((long) 1);
        pedidoDto.setProdutoName("Teste");
        pedidoDto.setIdProduto((long) 1);

        _pedidoService.addPedido(pedidoDto);
    }
    @Test
    public void getAllPedidos() {
        var paginationRequestDto = new PaginationRequestDTO();
        paginationRequestDto.setDescending(true);
        paginationRequestDto.setPageSize(10);
        paginationRequestDto.setPageNo(1);
        paginationRequestDto.setSortBy("id");

        _pedidoService.getAllPedidos(paginationRequestDto);
    }
    @Test
    public void closePedido() {

        var produto = new Produto();
        produto.setNomeProduto("Test");
        produto.setPreco(3);

        var pedidoDto = new PedidoDTO();
        pedidoDto.setId((long) 1);
        pedidoDto.setIdProduto((long) 1);

        var closeOrderDto = new CloseOrderDTO();
        closeOrderDto.setPedidoDto(pedidoDto);
        closeOrderDto.setPaymentValue(10);

        _pedidoService.closePedido(closeOrderDto);
    }
}
