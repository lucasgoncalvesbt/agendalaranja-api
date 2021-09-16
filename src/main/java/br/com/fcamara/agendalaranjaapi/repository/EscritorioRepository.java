package br.com.fcamara.agendalaranjaapi.repository;

import br.com.fcamara.agendalaranjaapi.model.Escritorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscritorioRepository extends JpaRepository<Escritorio, Long> {



}
