package br.com.fcamara.agendalaranjaapi.service.escritorio;

import br.com.fcamara.agendalaranjaapi.dto.EscritorioDTO;
import br.com.fcamara.agendalaranjaapi.exception.NotFoundException;
import br.com.fcamara.agendalaranjaapi.model.Escritorio;
import br.com.fcamara.agendalaranjaapi.repository.EscritorioRepository;
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
    public Escritorio getDeskById(Long id) {
        return escritorioRepository.findById(id).orElseThrow(() -> new NotFoundException(id.toString(), "Escritório"));
    }

    @Override
    public Escritorio registrarEscritorio(EscritorioDTO escritorioDTO) {
        Escritorio escritorioParaSalvar = Escritorio.builder()
                .local(escritorioDTO.getLocal())
                .capacidade(escritorioDTO.getCapacidade() * 0.4)
                .build();

        return escritorioRepository.save(escritorioParaSalvar);
    }

    @Override
    public Escritorio updateDesk(Long id, EscritorioDTO escritorioDTO) {
        Escritorio escritorio = escritorioRepository.findById(id).get();
        escritorio.setCapacidade(escritorioDTO.getCapacidade());
        escritorio.setLocal(escritorioDTO.getLocal());


        return escritorioRepository.save(escritorio);
    }

    @Override
    public void deleteDesk(Long id) {
        escritorioRepository.deleteById(id);
    }
}
