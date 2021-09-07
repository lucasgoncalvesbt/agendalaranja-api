package br.com.fcamara.hackatonapi.repository;

import br.com.fcamara.hackatonapi.model.Escritorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscritorioRepository extends JpaRepository<Escritorio, Long> {



}
