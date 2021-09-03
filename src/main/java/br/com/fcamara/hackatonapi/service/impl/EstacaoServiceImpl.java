package br.com.fcamara.hackatonapi.service.impl;

import br.com.fcamara.hackatonapi.dto.EstacaoDTO;
import br.com.fcamara.hackatonapi.exception.CapacityExceededException;
import br.com.fcamara.hackatonapi.exception.NotFoundException;
import br.com.fcamara.hackatonapi.model.Escritorio;
import br.com.fcamara.hackatonapi.model.Estacao;
import br.com.fcamara.hackatonapi.repository.EscritorioRepository;
import br.com.fcamara.hackatonapi.repository.EstacaoRepository;
import br.com.fcamara.hackatonapi.service.EstacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class EstacaoServiceImpl implements EstacaoService {

    private EscritorioRepository escritorioRepository;
    private EstacaoRepository estacaoRepository;

    @Autowired
    public EstacaoServiceImpl(EscritorioRepository escritorioRepository, EstacaoRepository estacaoRepository) {
        this.escritorioRepository = escritorioRepository;
        this.estacaoRepository = estacaoRepository;
    }


    @Override
    public List<Estacao> getAllStation() {
        return estacaoRepository.findAll();
    }


    @Override
    public Estacao getStationById(Long id) {
        return estacaoRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "Estação"));
    }

    @Override
    public Estacao createStation(EstacaoDTO estacaoDTO) {

        Escritorio escritorio = escritorioRepository.findById(estacaoDTO.getEscritorioId())
                .orElseThrow(() -> new NotFoundException(estacaoDTO.getEscritorioId(), "Escritório"));

        Double somaDosLugares = estacaoRepository.findByEscritorioId(estacaoDTO.getEscritorioId()).stream().mapToDouble(Estacao::getQtdLugares).sum();
        Double capacidade = escritorio.getCapacidade();

        Double somaAposIncrementoDeLugares = estacaoDTO.getQtdLugares() + somaDosLugares;
        if (somaAposIncrementoDeLugares > capacidade) {
            throw new CapacityExceededException(escritorio.getId(), somaAposIncrementoDeLugares - capacidade);
        }

        Estacao estacao = Estacao.builder()
                .qtdLugares(estacaoDTO.getQtdLugares())
                .escritorio(escritorio)
                .build();

        return estacaoRepository.save(estacao);
    }

    @Override
    public Estacao updateStation(Long id, EstacaoDTO estacaoDTO) {
        return null;
    }

    @Override
    public void deleteStation(Long id) {

    }
}
