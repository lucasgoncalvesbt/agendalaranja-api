package br.com.fcamara.agendalaranjaapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContaCriadaDTO {
    private String mensagem;
}
