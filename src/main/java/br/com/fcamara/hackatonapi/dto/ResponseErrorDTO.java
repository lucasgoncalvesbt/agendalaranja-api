package br.com.fcamara.hackatonapi.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseErrorDTO {

    private String message;

}
