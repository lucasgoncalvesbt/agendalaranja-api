package br.com.fcamara.hackatonapi.controller;

import br.com.fcamara.hackatonapi.dto.EscritorioDTO;
import br.com.fcamara.hackatonapi.service.EscritorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/escritorio")
public class EscritorioController {

    private EscritorioService escritorioService;

    @Autowired
    public EscritorioController(EscritorioService escritorioService) {
        this.escritorioService = escritorioService;
    }

    @GetMapping
    public ResponseEntity<List<EscritorioDTO>> getAllDesks() {
        List<EscritorioDTO> escritorios = EscritorioDTO.convertList(escritorioService.getAllDesks());
        return ResponseEntity.ok(escritorios);
    }

    @PostMapping
    public ResponseEntity<EscritorioDTO> createDesk(@RequestBody EscritorioDTO escritorioDTO) {
        EscritorioDTO escritorio = new EscritorioDTO(escritorioService.registrarEscritorio(escritorioDTO));
        return ResponseEntity.ok(escritorio);
    }

}
