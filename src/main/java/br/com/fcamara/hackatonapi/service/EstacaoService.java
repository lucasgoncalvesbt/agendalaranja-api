package br.com.fcamara.hackatonapi.service;

import br.com.fcamara.hackatonapi.dto.EstacaoDTO;
import br.com.fcamara.hackatonapi.model.Estacao;

import java.util.List;

public interface EstacaoService {
    List<Estacao> getAllStation();

    Estacao createStation(EstacaoDTO estacaoDTO);
}
