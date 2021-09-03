package br.com.fcamara.hackatonapi.service.impl;

import br.com.fcamara.hackatonapi.dto.EscritorioDTO;
import br.com.fcamara.hackatonapi.model.Escritorio;
import br.com.fcamara.hackatonapi.repository.EscritorioRepository;
import br.com.fcamara.hackatonapi.service.EscritorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EscritorioServiceImpl implements EscritorioService {

    private EscritorioRepository escritorioRepository;

    @Autowired
    public EscritorioServiceImpl(EscritorioRepository escritorioRepository) {
        this.escritorioRepository = escritorioRepository;
    }



    @Override
    public List<Escritorio> getAllDesks() {
        return escritorioRepository.findAll();
    }

    @Override
    public Escritorio registrarEscritorio(EscritorioDTO escritorioDTO) {
        Escritorio escritorioParaSalvar = Escritorio.builder()
                .local(escritorioDTO.getLocal())
                .capacidade(escritorioDTO.getCapacidade() * 0.4)
                .build();

        return escritorioRepository.save(escritorioParaSalvar);
    }
}
