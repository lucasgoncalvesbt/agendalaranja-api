package br.com.fcamara.hackatonapi.controller;

import br.com.fcamara.hackatonapi.dto.EscritorioDTO;
import br.com.fcamara.hackatonapi.dto.EstacaoDTO;
import br.com.fcamara.hackatonapi.model.Estacao;
import br.com.fcamara.hackatonapi.service.EstacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/estacao")
public class EstacaoController {

    private EstacaoService estacaoService;

    public EstacaoController(EstacaoService estacaoService) {
        this.estacaoService = estacaoService;
    }


    @GetMapping
    public ResponseEntity<List<EstacaoDTO>> getAllStation() {
        List<EstacaoDTO> estacoes = EstacaoDTO.convertList(estacaoService.getAllStation());
        return ResponseEntity.ok(estacoes);
    }

    @PostMapping
    public ResponseEntity<EstacaoDTO> createDesk(@RequestBody EstacaoDTO estacaoDTO) {
        EstacaoDTO estacao = new EstacaoDTO(estacaoService.createStation(estacaoDTO));
        return ResponseEntity.ok(estacao);
    }
}
