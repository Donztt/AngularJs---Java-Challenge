package com.example.BackEnd.repository;

import com.example.BackEnd.models.Pedido;
import com.example.BackEnd.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto,Long> {

}
