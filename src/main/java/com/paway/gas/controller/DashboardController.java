package com.paway.gas.controller;

import com.paway.gas.dto.DashboardResumenDTO;
import com.paway.gas.dto.PedidoDTO;
import com.paway.gas.service.DashboardService;
import com.paway.gas.service.PedidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/admin-panel.html";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        DashboardResumenDTO resumen = dashboardService.obtenerResumenDashboard();
        List<PedidoDTO> pedidos = pedidoService.listarPedidos();
        model.addAttribute("stats", resumen);
        model.addAttribute("pedidos", pedidos);
        return "dashboard";
    }

    @GetMapping("/api/dashboard")
    @ResponseBody
    public DashboardResumenDTO getDashboardResumen() {
        return dashboardService.obtenerResumenDashboard();
    }

    @GetMapping("/api/pedidos")
    @ResponseBody
    public List<PedidoDTO> listarPedidos() {
        return pedidoService.listarPedidos();
    }
}
