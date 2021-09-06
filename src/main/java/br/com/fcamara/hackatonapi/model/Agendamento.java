package br.com.fcamara.hackatonapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "nome_consultor", nullable = false)
    private String nomeConsultor;

    @Column(name = "email_consultor", nullable = false)
    private String emailConsultor;

    @Column(name = "data_agendada", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataAgendada;

    @ManyToOne
    private Estacao estacao;
}
