package br.com.fcamara.agendalaranjaapi.controller;

import br.com.fcamara.agendalaranjaapi.dto.AgendamentoDTO;
import br.com.fcamara.agendalaranjaapi.service.agendamento.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/agendamento")
@CrossOrigin(origins = "*")
public class AgendamentoController {

    private AgendamentoService agendamentoService;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }



    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> getAllScheduling(@RequestParam(name = "estacaoId", required = false) Long estacaoId, @RequestParam(name = "emailConsultor", required = false) String emailConsultor) {
        List<AgendamentoDTO> agendamentos = null;
        if(estacaoId == null && !emailConsultor.isEmpty()) {
            agendamentos = AgendamentoDTO.convertList(agendamentoService.getAllSchedulingByConsultor(emailConsultor));
        } else if(estacaoId != null) {
            agendamentos = AgendamentoDTO.convertList(agendamentoService.getAllScheduling(estacaoId));
        }

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> getSchedulingByIf(@PathVariable UUID id) {
        AgendamentoDTO agendamento = new AgendamentoDTO(agendamentoService.getSchedulingById(id));
        return ResponseEntity.ok(agendamento);
    }

    @PostMapping
    public ResponseEntity<AgendamentoDTO>  createScheduling(@RequestBody AgendamentoDTO agendamentoDTO) {
        AgendamentoDTO agendamento = new AgendamentoDTO(agendamentoService.createScheduling(agendamentoDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> updateScheduling(@PathVariable UUID id, @RequestBody AgendamentoDTO agendamentoDTO) {
        AgendamentoDTO agendamento = new AgendamentoDTO(agendamentoService.updateScheduling(id, agendamentoDTO));
        return ResponseEntity.ok(agendamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScheduling(@PathVariable UUID id) {
        agendamentoService.deleteScheduling(id);
        return ResponseEntity.noContent().build();
    }
}
