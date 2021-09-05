package br.com.fcamara.hackatonapi.service.agendamento;

import br.com.fcamara.hackatonapi.dto.AgendamentoDTO;
import br.com.fcamara.hackatonapi.exception.NotFoundException;
import br.com.fcamara.hackatonapi.model.Agendamento;
import br.com.fcamara.hackatonapi.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgendamentoServiceImpl implements AgendamentoService{

    private AgendamentoRepository agendamentoRepository;

    @Autowired
    public AgendamentoServiceImpl(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }



    @Override
    public List<Agendamento> getAllScheduling() {
        return agendamentoRepository.findAll();
    }

    @Override
    public Agendamento getSchedulingById(UUID id) {
        return agendamentoRepository.findById(id).orElseThrow(() -> new NotFoundException(id.toString(), "Agendamento"));
    }

    @Override
    public Agendamento createScheduling(AgendamentoDTO agendamentoDTO) {
        return null;
    }

    @Override
    public Agendamento updateScheduling(Long id, AgendamentoDTO agendamentoDTO) {
        return null;
    }

    @Override
    public void deleteScheduling(Long id) {

    }
}
