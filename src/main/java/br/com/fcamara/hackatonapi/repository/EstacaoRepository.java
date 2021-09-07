package br.com.fcamara.hackatonapi.repository;

import br.com.fcamara.hackatonapi.model.Estacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface EstacaoRepository extends JpaRepository<Estacao, Long> {

    List<Estacao> findByEscritorioId(Long escritorioId);

}
