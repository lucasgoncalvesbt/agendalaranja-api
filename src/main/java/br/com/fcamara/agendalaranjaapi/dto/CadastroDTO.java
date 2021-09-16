package br.com.fcamara.agendalaranjaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastroDTO {

    private String email;

    private String nome;

    private String sobrenome;

    private String senha;

}
