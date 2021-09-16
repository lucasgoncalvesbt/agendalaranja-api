package br.com.fcamara.agendalaranjaapi.service.escritorio;

import br.com.fcamara.agendalaranjaapi.dto.EscritorioDTO;
import br.com.fcamara.agendalaranjaapi.model.Escritorio;

import java.util.List;


public interface EscritorioService {

    Escritorio registrarEscritorio(EscritorioDTO escritorioDTO);

    List<Escritorio> getAllDesks();

    Escritorio getDeskById(Long id);

    Escritorio updateDesk(Long id, EscritorioDTO escritorioDTO);

    void deleteDesk(Long id);
}
