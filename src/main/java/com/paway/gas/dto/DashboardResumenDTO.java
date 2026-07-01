package com.paway.gas.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DashboardResumenDTO {

    private long totalOrders;
    private long pendingOrders;
    private long inTransitOrders;
    private long deliveredOrders;
    private BigDecimal totalSales = BigDecimal.ZERO;
    private long lowStockProducts;
    private List<PedidoDTO> pedidosRecientes = new ArrayList<>();

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public long getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(long pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public long getInTransitOrders() {
        return inTransitOrders;
    }

    public void setInTransitOrders(long inTransitOrders) {
        this.inTransitOrders = inTransitOrders;
    }

    public long getDeliveredOrders() {
        return deliveredOrders;
    }

    public void setDeliveredOrders(long deliveredOrders) {
        this.deliveredOrders = deliveredOrders;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    public long getLowStockProducts() {
        return lowStockProducts;
    }

    public void setLowStockProducts(long lowStockProducts) {
        this.lowStockProducts = lowStockProducts;
    }

    public List<PedidoDTO> getPedidosRecientes() {
        return pedidosRecientes;
    }

    public void setPedidosRecientes(List<PedidoDTO> pedidosRecientes) {
        this.pedidosRecientes = pedidosRecientes;
    }
}
