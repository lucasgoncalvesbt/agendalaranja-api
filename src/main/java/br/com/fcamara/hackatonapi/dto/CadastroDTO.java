package br.com.fcamara.hackatonapi.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CadastroDTO {

    private String email;

    private String nome;

    private String sobrenome;

    private String senha;

}
