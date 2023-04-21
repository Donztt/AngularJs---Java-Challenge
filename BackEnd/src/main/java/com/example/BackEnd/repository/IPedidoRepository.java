package com.example.BackEnd.repository;

import com.example.BackEnd.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido,Long> {
    @Query("SELECT p.id ,p.qtdProduto, pr.nomeProduto AS nomeProduto "
            + "FROM Pedido p "
            + "JOIN Produto pr ON p.idProduto = pr.id")
    List<Object[]> findAllPedidosWithProdutoName();
}
