package com.example.BackEnd.services;

import com.example.BackEnd.DTO.PedidoDTO;
import com.example.BackEnd.repository.IPedidoRepository;
import com.example.BackEnd.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository _pedidoRepository;

    public Page<PedidoDTO> getAllPedidos(int numPage, int pageSize) {
        Pageable pageable = PageRequest.of(numPage, pageSize);

        var pedidos = _pedidoRepository.findAllPedidosWithProdutoName();

        List<PedidoDTO> pedidoDtoList = new ArrayList<>();
        for (var pedido: pedidos) {
            var pedidoDto = new PedidoDTO();

            pedidoDto.setId((Long) pedido[0]);
            pedidoDto.setQtdProduto((int) pedido[1]);
            pedidoDto.setProdutoName((String) pedido[2]);

            pedidoDtoList.add(pedidoDto);
        }
        return new PageImpl<>(pedidoDtoList, pageable, pedidos.size());
    }

    public PedidoDTO addPedido(PedidoDTO pedido) {
       return _pedidoRepository.save(pedido.toModel()).toDTO();
    }

    public PedidoDTO getPedido(long idPedido) {
        return _pedidoRepository.getById(idPedido).toDTO();
    }

    public HttpStatus removePedido(PedidoDTO pedidoDto) {
        _pedidoRepository.delete(pedidoDto.toModel());

        return HttpStatus.OK;
    }
}
