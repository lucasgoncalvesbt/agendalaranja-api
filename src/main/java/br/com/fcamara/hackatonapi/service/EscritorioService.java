package br.com.fcamara.hackatonapi.service;

import br.com.fcamara.hackatonapi.dto.EscritorioDTO;
import br.com.fcamara.hackatonapi.model.Escritorio;

import java.util.List;


public interface EscritorioService {

    Escritorio registrarEscritorio(EscritorioDTO escritorioDTO);

    List<Escritorio> getAllDesks();
}
