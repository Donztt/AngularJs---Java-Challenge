package com.example.BackEnd.services;

import com.example.BackEnd.DTO.ProdutoDTO;
import com.example.BackEnd.models.Produto;
import com.example.BackEnd.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private IProdutoRepository _produtoRepository;

    public List<ProdutoDTO> getAllProdutos() {
        var produtos = _produtoRepository.findAll();

        List<ProdutoDTO> produtosDto = new ArrayList<>();
        for (var produto: produtos) {
            produtosDto.add(produto.toDTO());
        }
        return produtosDto;
    }

    public ProdutoDTO addProduto(ProdutoDTO produto) {
       return _produtoRepository.save(produto.toModel()).toDTO();
    }
}
