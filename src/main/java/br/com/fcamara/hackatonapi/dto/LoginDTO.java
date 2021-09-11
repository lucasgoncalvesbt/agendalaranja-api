package br.com.fcamara.hackatonapi.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginDTO {

    private String email;

    private String nome;

    private String senha;

}
