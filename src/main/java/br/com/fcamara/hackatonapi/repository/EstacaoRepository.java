package br.com.fcamara.hackatonapi.repository;

import br.com.fcamara.hackatonapi.model.Estacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacaoRepository extends JpaRepository<Estacao, Long> {
}
