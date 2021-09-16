package br.com.fcamara.hackatonapi.controller;

import br.com.fcamara.hackatonapi.dto.EstacaoDTO;
import br.com.fcamara.hackatonapi.service.estacao.EstacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/estacao")
@CrossOrigin(origins = "*")
public class EstacaoController {

    private EstacaoService estacaoService;

    public EstacaoController(EstacaoService estacaoService) {
        this.estacaoService = estacaoService;
    }


    @GetMapping
    public ResponseEntity<List<EstacaoDTO>> getAllStation(@RequestParam(name = "escritorioId", required = false) Long escritorioId) {
        List<EstacaoDTO> estacoes;
        if(escritorioId != null) {
            estacoes = EstacaoDTO.convertList(estacaoService.getAllStationByDeskId(escritorioId));
        } else {
            estacoes = EstacaoDTO.convertList(estacaoService.getAllStation());
        }
        return ResponseEntity.ok(estacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstacaoDTO> getStationById(@PathVariable Long id) {
        EstacaoDTO estacao = new EstacaoDTO(estacaoService.getStationById(id));
        return ResponseEntity.ok(estacao);
    }

    @PostMapping
    public ResponseEntity<EstacaoDTO> createStation(@RequestBody EstacaoDTO estacaoDTO) {
        EstacaoDTO estacao = new EstacaoDTO(estacaoService.createStation(estacaoDTO));
        return ResponseEntity.ok(estacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstacaoDTO> updateStation(@PathVariable Long id, @RequestBody EstacaoDTO estacaoDTO) {
        EstacaoDTO estacao = new EstacaoDTO(estacaoService.updateStation(id, estacaoDTO));
        return ResponseEntity.ok(estacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStation(@PathVariable Long id) {
        estacaoService.deleteStation(id);
        return ResponseEntity.noContent().build();
    }
}
