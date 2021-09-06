package br.com.fcamara.hackatonapi.service.agendamento;

import br.com.fcamara.hackatonapi.dto.AgendamentoDTO;
import br.com.fcamara.hackatonapi.model.Agendamento;

import java.util.List;
import java.util.UUID;

public interface AgendamentoService {

    List<Agendamento> getAllScheduling();

    Agendamento getSchedulingById(UUID id);

    Agendamento createScheduling(AgendamentoDTO agendamentoDTO);

    Agendamento updateScheduling(UUID id, AgendamentoDTO agendamentoDTO);

    void deleteScheduling(UUID id);

}
