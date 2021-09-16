package br.com.fcamara.agendalaranjaapi.service.agendamento;

import br.com.fcamara.agendalaranjaapi.dto.AgendamentoDTO;
import br.com.fcamara.agendalaranjaapi.model.Agendamento;

import java.util.List;
import java.util.UUID;

public interface AgendamentoService {

    List<Agendamento> getAllScheduling(Long estacaoId);

    Agendamento getSchedulingById(UUID id);

    Agendamento createScheduling(AgendamentoDTO agendamentoDTO);

    Agendamento updateScheduling(UUID id, AgendamentoDTO agendamentoDTO);

    void deleteScheduling(UUID id);

    List<Agendamento> getAllSchedulingByConsultor(String emailConsultor);
}
