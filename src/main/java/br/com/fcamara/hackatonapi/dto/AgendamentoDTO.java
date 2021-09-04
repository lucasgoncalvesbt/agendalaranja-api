package br.com.fcamara.hackatonapi.dto;

import br.com.fcamara.hackatonapi.model.Agendamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO {

    private UUID id;
    private String nomeConsultor;
    private String emailConsultor;
    private LocalDateTime dataAgendada;
    private Long estacaoId;

    public AgendamentoDTO(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.nomeConsultor = agendamento.getNomeConsultor();
        this.emailConsultor = agendamento.getEmailConsultor();
        this.dataAgendada = agendamento.getDataAgendada();
        this.estacaoId = agendamento.getEstacao().getId();
    }

    public static List<AgendamentoDTO> convertList(List<Agendamento> agendamentos) {
        return agendamentos.stream().map(AgendamentoDTO::new).collect(Collectors.toList());
    }
}
