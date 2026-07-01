package com.paway.gas.service;

import com.paway.gas.dto.ClienteDTO;
import com.paway.gas.dto.PedidoDTO;
import com.paway.gas.entity.Cliente;
import com.paway.gas.entity.EstadoPedido;
import com.paway.gas.entity.Pedido;
import com.paway.gas.repository.PedidoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PedidoDTO> listarPedidos() {
        List<Pedido> lista = pedidoRepository.findAllByOrderByFechaPedidoDesc();
        return lista.stream().map(this::convertirDTO).collect(Collectors.toList());
    }

    public List<PedidoDTO> listarPedidosPorEstado(EstadoPedido estado) {
        List<Pedido> lista = pedidoRepository.findByEstadoOrderByFechaPedidoDesc(estado);
        return lista.stream().map(this::convertirDTO).collect(Collectors.toList());
    }

    public List<PedidoDTO> buscarPorNombreCliente(String nombre) {
        List<Pedido> lista = pedidoRepository.buscarPorNombreClienteJPQL(nombre);
        return lista.stream().map(this::convertirDTO).collect(Collectors.toList());
    }

    public List<PedidoDTO> buscarPorEstadoSQL(EstadoPedido estado) {
        List<Pedido> lista = pedidoRepository.buscarPorEstadoSQL(estado.name());
        return lista.stream().map(this::convertirDTO).collect(Collectors.toList());
    }

    public PedidoDTO convertirDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(pedido.getIdPedido());
        dto.setCliente(convertirClienteDTO(pedido.getCliente()));
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setEstado(pedido.getEstado());
        dto.setTotal(pedido.getTotal());
        dto.setDireccionEntrega(pedido.getDireccionEntrega());
        dto.setObservacion(pedido.getObservacion());
        dto.setPagado(Boolean.TRUE.equals(pedido.getPagado()));
        return dto;
    }

    private ClienteDTO convertirClienteDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setNombreCompleto(cliente.getNombreCompleto());
        dto.setTelefono(cliente.getTelefono());
        dto.setDireccion(cliente.getDireccion());
        dto.setEmail(cliente.getEmail());
        return dto;
    }
}
