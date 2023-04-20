package com.example.BackEnd.services;

import com.example.BackEnd.DTO.PedidoDTO;
import com.example.BackEnd.models.Pedido;
import com.example.BackEnd.repository.IPedidoRepository;
import com.example.BackEnd.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository _pedidoRepository;

    @Autowired
    private IProdutoRepository _produtoRepository;

    public List<Pedido> getAllPedidos() {
        return _pedidoRepository.findAll();
    }

    public PedidoDTO novoPedido(PedidoDTO pedido) {
       return _pedidoRepository.save(pedido.toModel()).toDTO();
    }

    public PedidoDTO getPedido(long idPedido) {
        return _pedidoRepository.getById(idPedido).toDTO();
    }

    public void removePedido(PedidoDTO pedidoDto) {
        _pedidoRepository.delete(pedidoDto.toModel());
    }
}
