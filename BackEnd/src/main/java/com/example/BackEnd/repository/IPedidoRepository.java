package com.example.BackEnd.repository;

import com.example.BackEnd.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido,Long> {
    @Query("SELECT p.id, p.qtdProduto, pr.nomeProduto, p.createdDate, pr.preco " +
            "FROM Pedido p " +
            "JOIN Produto pr ON p.idProduto = pr.id " +
            "ORDER BY p.id DESC")
    List<Object[]> findAllPedidosWithProdutoName();

    @Query("SELECT (p.qtdProduto * pr.preco) AS totalValue " +
            "FROM Pedido p " +
            "JOIN Produto pr ON p.idProduto = pr.id " +
            "WHERE p.id =:id ")
    Object[] getTotalValueById(@Param("id") long id);
}
