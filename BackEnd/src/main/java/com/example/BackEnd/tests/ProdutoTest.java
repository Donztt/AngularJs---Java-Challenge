package com.example.BackEnd.tests;

import com.example.BackEnd.DTO.CloseOrderDTO;
import com.example.BackEnd.DTO.PaginationRequestDTO;
import com.example.BackEnd.DTO.PedidoDTO;
import com.example.BackEnd.DTO.ProdutoDTO;
import com.example.BackEnd.models.Produto;
import com.example.BackEnd.services.PedidoService;
import com.example.BackEnd.services.ProdutoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProdutoTest {
    @Autowired
    private ProdutoService _produtoService;
    @Test
    public void addProduto() {
        var produtoDto = new ProdutoDTO();
        produtoDto.setNomeProduto("Teste");
        produtoDto.setPreco(3);

        _produtoService.addProduto(produtoDto);
    }
    @Test
    public void getAllProdutos() {

        var produtoDto = new ProdutoDTO();
        produtoDto.setNomeProduto("Teste");
        produtoDto.setPreco(1);

        var produtoDto2 = new ProdutoDTO();
        produtoDto2.setNomeProduto("Teste2");
        produtoDto2.setPreco(2);

        var produtoDto3 = new ProdutoDTO();
        produtoDto3.setNomeProduto("Teste3");
        produtoDto3.setPreco(3);

        _produtoService.getAllProdutos();
    }
}
