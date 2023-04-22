package com.example.BackEnd.controller;

import com.example.BackEnd.DTO.PaginationRequestDTO;
import com.example.BackEnd.DTO.PedidoDTO;
import com.example.BackEnd.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pedido")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {
	@Autowired
	private PedidoService _pedidoService;

	@PostMapping("/PaginationPedidos")
	public Page<PedidoDTO> listPedidos(@RequestBody PaginationRequestDTO paginationRequestDTO) {
		return _pedidoService.getAllPedidos(paginationRequestDTO.pageNo,paginationRequestDTO.pageSize);
	}

	@PostMapping("/AddPedido")
	public PedidoDTO addPedido(@RequestBody PedidoDTO pedidoDTO) {
		return _pedidoService.addPedido(pedidoDTO);
	}
}
