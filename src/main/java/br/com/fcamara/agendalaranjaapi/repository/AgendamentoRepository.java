package br.com.fcamara.agendalaranjaapi.repository;

import br.com.fcamara.agendalaranjaapi.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {
    List<Agendamento> findByEstacao_Id(Long estacaoId);

    List<Agendamento> findByDataAgendada(LocalDate dataAgendada);

    List<Agendamento> findByEmailConsultor(String emailConsultor);
}
