package br.com.fcamara.agendalaranjaapi.service.estacao;

import br.com.fcamara.agendalaranjaapi.dto.EstacaoDTO;
import br.com.fcamara.agendalaranjaapi.model.Estacao;

import java.util.List;

public interface EstacaoService {
    List<Estacao> getAllStation();

    Estacao createStation(EstacaoDTO estacaoDTO);

    Estacao getStationById(Long id);

    Estacao updateStation(Long id, EstacaoDTO estacaoDTO);

    void deleteStation(Long id);

    List<Estacao> getAllStationByDeskId(Long escritorioId);
}
