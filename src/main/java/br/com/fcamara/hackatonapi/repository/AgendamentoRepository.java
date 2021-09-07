package br.com.fcamara.hackatonapi.repository;

import br.com.fcamara.hackatonapi.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {
    List<Agendamento> findByEstacao_Id(Long estacaoId);

    List<Agendamento> findByDataAgendada(LocalDate dataAgendada);
}
