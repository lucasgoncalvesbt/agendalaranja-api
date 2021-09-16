package br.com.fcamara.hackatonapi.service.estacao;

import br.com.fcamara.hackatonapi.dto.EstacaoDTO;
import br.com.fcamara.hackatonapi.model.Estacao;

import java.util.List;

public interface EstacaoService {
    List<Estacao> getAllStation();

    Estacao createStation(EstacaoDTO estacaoDTO);

    Estacao getStationById(Long id);

    Estacao updateStation(Long id, EstacaoDTO estacaoDTO);

    void deleteStation(Long id);

    List<Estacao> getAllStationByDeskId(Long escritorioId);
}
