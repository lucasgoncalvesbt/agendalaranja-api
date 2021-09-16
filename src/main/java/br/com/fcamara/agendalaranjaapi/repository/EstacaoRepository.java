package br.com.fcamara.agendalaranjaapi.repository;

import br.com.fcamara.agendalaranjaapi.model.Estacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstacaoRepository extends JpaRepository<Estacao, Long> {

    List<Estacao> findByEscritorioId(Long escritorioId);

}
