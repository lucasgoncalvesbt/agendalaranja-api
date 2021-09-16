package br.com.fcamara.agendalaranjaapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeEstacao;
    private Double qtdLugares;

    @ManyToOne
    private Escritorio escritorio;

    @OneToMany(mappedBy = "estacao")
    private List<Agendamento> agendamentos;
}
