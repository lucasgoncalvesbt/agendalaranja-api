package br.com.fcamara.agendalaranjaapi.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginDTO {

    private String email;

    private String senha;

}
