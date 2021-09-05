package br.com.fcamara.hackatonapi.repository;

import br.com.fcamara.hackatonapi.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {
}
