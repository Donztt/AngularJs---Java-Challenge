package com.example.BackEnd.controller;

import com.example.BackEnd.DTO.PaginationRequestDTO;
import com.example.BackEnd.DTO.PedidoDTO;
import com.example.BackEnd.DTO.ProdutoDTO;
import com.example.BackEnd.models.Produto;
import com.example.BackEnd.services.PedidoService;
import com.example.BackEnd.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	@Autowired
	private ProdutoService _produtoService;

	@GetMapping("/ListProdutos")
	public List<Produto> listProdutos() {

		return _produtoService.getAllProdutos();
	}

	@PostMapping("/AddProduto")
	public void addProduto(ProdutoDTO produtoDto){
		_produtoService.addProduto(produtoDto);
	}
}
