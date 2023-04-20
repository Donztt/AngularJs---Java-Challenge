package com.example.BackEnd.controller;

import com.example.BackEnd.models.Pedido;
import com.example.BackEnd.models.Produto;
import com.example.BackEnd.services.PedidoService;
import com.example.BackEnd.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestController {
	@Autowired
	private PedidoService _pedidoService;
	@Autowired
	private ProdutoService _produtoService;
	@GetMapping("/ListPedidos")
	public List<Pedido> listPedidos() {
		return _pedidoService.getAllPedidos();
	}
	@GetMapping("/ListProdutos")
	public List<Produto> listProdutos() {

		return _produtoService.getAllProdutos();
	}
}
