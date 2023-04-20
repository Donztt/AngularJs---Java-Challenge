package com.example.BackEnd.services;

import com.example.BackEnd.DTO.ProdutoDTO;
import com.example.BackEnd.models.Produto;
import com.example.BackEnd.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private IProdutoRepository _produtoRepository;

    public List<Produto> getAllProdutos() {
        return (List<Produto>) _produtoRepository.findAll();
    }

    public ProdutoDTO novoProduto(ProdutoDTO produto) {
        return _produtoRepository.save(produto.toModel()).toDTO();
    }
}
