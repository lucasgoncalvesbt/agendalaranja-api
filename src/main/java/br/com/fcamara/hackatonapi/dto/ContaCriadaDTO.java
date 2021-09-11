package br.com.fcamara.hackatonapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContaCriadaDTO {
    private String mensagem;
}
