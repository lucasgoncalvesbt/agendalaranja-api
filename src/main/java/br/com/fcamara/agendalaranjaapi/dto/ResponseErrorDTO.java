package br.com.fcamara.agendalaranjaapi.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseErrorDTO {

    private String message;

}
