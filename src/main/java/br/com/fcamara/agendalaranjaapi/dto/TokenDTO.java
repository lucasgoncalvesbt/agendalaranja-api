package br.com.fcamara.agendalaranjaapi.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenDTO {
    private String token;
    private String type;
}
