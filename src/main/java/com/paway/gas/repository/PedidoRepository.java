package com.paway.gas.repository;

import com.paway.gas.entity.EstadoPedido;
import com.paway.gas.entity.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findAllByOrderByFechaPedidoDesc();

    List<Pedido> findByEstadoOrderByFechaPedidoDesc(EstadoPedido estado);

    long countByEstado(EstadoPedido estado);

    @Query("SELECT p FROM Pedido p JOIN p.cliente c WHERE LOWER(c.nombreCompleto) LIKE LOWER(CONCAT('%', :nombre, '%')) ORDER BY p.fechaPedido DESC")
    List<Pedido> buscarPorNombreClienteJPQL(@Param("nombre") String nombre);

    @Query(value = "SELECT p.* FROM pedidos p INNER JOIN clientes c ON p.cliente_id = c.id_cliente WHERE LOWER(c.nombre_completo) LIKE LOWER(CONCAT('%', :nombre, '%')) ORDER BY p.fecha_pedido DESC", nativeQuery = true)
    List<Pedido> buscarPorNombreClienteSQL(@Param("nombre") String nombre);

    @Query("SELECT p FROM Pedido p WHERE p.estado = :estado ORDER BY p.fechaPedido DESC")
    List<Pedido> buscarPorEstadoJPQL(@Param("estado") EstadoPedido estado);

    @Query(value = "SELECT * FROM pedidos WHERE estado = :estado ORDER BY fecha_pedido DESC", nativeQuery = true)
    List<Pedido> buscarPorEstadoSQL(@Param("estado") String estado);
}
