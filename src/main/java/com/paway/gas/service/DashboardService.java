package com.paway.gas.service;

import com.paway.gas.dto.DashboardResumenDTO;
import com.paway.gas.dto.PedidoDTO;
import com.paway.gas.entity.EstadoPedido;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private PedidoService pedidoService;

    public DashboardResumenDTO obtenerResumenDashboard() {
        List<PedidoDTO> pedidos = pedidoService.listarPedidos();

        DashboardResumenDTO resumen = new DashboardResumenDTO();
        resumen.setTotalOrders(pedidos.size());
        resumen.setPendingOrders(contarPorEstado(pedidos, EstadoPedido.PENDIENTE));
        resumen.setInTransitOrders(contarPorEstado(pedidos, EstadoPedido.EN_CAMINO));
        resumen.setDeliveredOrders(contarPorEstado(pedidos, EstadoPedido.ENTREGADO));
        resumen.setTotalSales(calcularVentas(pedidos));
        resumen.setLowStockProducts(0L);
        resumen.setPedidosRecientes(pedidos.stream().limit(6).collect(Collectors.toList()));
        return resumen;
    }

    private long contarPorEstado(List<PedidoDTO> pedidos, EstadoPedido estado) {
        return pedidos.stream()
                .filter(pedido -> pedido.getEstado() == estado)
                .count();
    }

    private BigDecimal calcularVentas(List<PedidoDTO> pedidos) {
        return pedidos.stream()
                .filter(pedido -> Boolean.TRUE.equals(pedido.getPagado()))
                .map(PedidoDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
