package br.com.fcamara.hackatonapi.service.impl;

import br.com.fcamara.hackatonapi.dto.EstacaoDTO;
import br.com.fcamara.hackatonapi.exception.CapacityExceededException;
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
    public Estacao createStation(EstacaoDTO estacaoDTO) {
        Double somaDosLugares = estacaoRepository.findByEscritorioId(estacaoDTO.getEscritorioId()).stream().mapToDouble(Estacao::getQtdLugares).sum();
        Double capacidade = escritorioRepository.findById(estacaoDTO.getEscritorioId()).get().getCapacidade();
        Double somaAposIncremento = estacaoDTO.getQtdLugares() + somaDosLugares;;

        if (somaAposIncremento > capacidade) {
            throw new CapacityExceededException(estacaoDTO.getEscritorioId(), somaAposIncremento - capacidade);
        }

        Estacao estacao = Estacao.builder()
                .qtdLugares(estacaoDTO.getQtdLugares())
                .escritorio(escritorioRepository.findById(estacaoDTO.getEscritorioId()).get())
                .build();

        return estacaoRepository.save(estacao);
    }
}
