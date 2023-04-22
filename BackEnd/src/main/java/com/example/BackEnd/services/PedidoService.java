package com.example.BackEnd.services;

import com.example.BackEnd.DTO.CloseOrderDTO;
import com.example.BackEnd.DTO.PaginationRequestDTO;
import com.example.BackEnd.DTO.PedidoDTO;
import com.example.BackEnd.repository.IPedidoRepository;
import com.example.BackEnd.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository _pedidoRepository;

    public Page<PedidoDTO> getAllPedidos(PaginationRequestDTO paginationRequestDTO) {

        var pedidos = _pedidoRepository.findAllPedidosWithProdutoName();

        List<PedidoDTO> pedidoDtoList = new ArrayList<>();
        NumberFormat currencyFormater = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        for (var pedido: pedidos) {
            var pedidoDto = new PedidoDTO();

            pedidoDto.setId((Long) pedido[0]);
            pedidoDto.setQtdProduto((int) pedido[1]);
            pedidoDto.setProdutoName((String) pedido[2]);
            pedidoDto.setCreatedDate((Date) pedido[3]);
            pedidoDto.setTotalValue(currencyFormater.format((Float) pedido[4] * (int)pedido[1]));

            pedidoDtoList.add(pedidoDto);
        }

        Pageable pageable;

        if (paginationRequestDTO.descending){
             pageable = PageRequest.of(paginationRequestDTO.pageNo, paginationRequestDTO.pageSize,Sort.by(paginationRequestDTO.sortBy).descending());
        }
        else{
             pageable = PageRequest.of(paginationRequestDTO.pageNo, paginationRequestDTO.pageSize,Sort.by(paginationRequestDTO.sortBy));
        }
        return new PageImpl<>(pedidoDtoList, pageable, pedidos.size());
    }

    public PedidoDTO addPedido(PedidoDTO pedido) {
        pedido.setCreatedDate(new Date());
       return _pedidoRepository.save(pedido.toModel()).toDTO();
    }

    public PedidoDTO getPedido(long idPedido) {
        return _pedidoRepository.getById(idPedido).toDTO();
    }

    public HttpStatus removePedido(PedidoDTO pedidoDto) {
        _pedidoRepository.delete(pedidoDto.toModel());

        return HttpStatus.OK;
    }

    public CloseOrderDTO closePedido(CloseOrderDTO closeOrderDto) {
        var totalValue  =  _pedidoRepository.getTotalValueById(closeOrderDto.getPedidoDto().getId());

        NumberFormat currencyFormater = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        var resultCloseOrderDto = new CloseOrderDTO();

        if (totalValue != null){
            if (closeOrderDto.getPaymentValue() >= (float) totalValue[0]){
                resultCloseOrderDto.setResultValue(currencyFormater.format( closeOrderDto.getPaymentValue() - (float) totalValue[0]));
                _pedidoRepository.delete(closeOrderDto.getPedidoDto().toModel());
            }
            else{
                throw new RuntimeException("Valor recebido menor que o valor total da conta");
            }
        }
        else{
            throw new RuntimeException("Ocorreu um problema no sistema");
        }

        return resultCloseOrderDto;
    }
}
