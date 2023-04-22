package com.example.BackEnd.controller;

import com.example.BackEnd.DTO.CloseOrderDTO;
import com.example.BackEnd.DTO.PaginationRequestDTO;
import com.example.BackEnd.DTO.PedidoDTO;
import com.example.BackEnd.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pedido")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {
	@Autowired
	private PedidoService _pedidoService;

	@PostMapping("/PaginationPedidos")
	public Page<PedidoDTO> listPedidos(@RequestBody PaginationRequestDTO paginationRequestDTO) {
		return _pedidoService.getAllPedidos(paginationRequestDTO);
	}
	@PostMapping("/AddPedido")
	public PedidoDTO addPedido(@RequestBody PedidoDTO pedidoDTO) {
		return _pedidoService.addPedido(pedidoDTO);
	}
	@PostMapping("/ClosePedido")
	public ResponseEntity closePedido(@RequestBody CloseOrderDTO closeOrderDto) {
		try{
			return ResponseEntity.ok(_pedidoService.closePedido(closeOrderDto));
		}
		catch (Exception ex){
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
}
