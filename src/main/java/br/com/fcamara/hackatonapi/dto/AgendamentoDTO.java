package br.com.fcamara.hackatonapi.dto;

import br.com.fcamara.hackatonapi.model.Agendamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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
    private Long estacaoId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataAgendada;



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
