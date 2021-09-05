package br.com.fcamara.hackatonapi.controller;

import br.com.fcamara.hackatonapi.dto.AgendamentoDTO;
import br.com.fcamara.hackatonapi.model.Agendamento;
import br.com.fcamara.hackatonapi.service.agendamento.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/agendamento")
public class AgendamentoController {

    private AgendamentoService agendamentoService;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }



    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> getAllScheduling() {
        List<AgendamentoDTO> agendamentos = AgendamentoDTO.convertList(agendamentoService.getAllScheduling());
        return ResponseEntity.ok(agendamentos);
    }
}
