package br.com.fcamara.hackatonapi.dto;

import br.com.fcamara.hackatonapi.model.Escritorio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EscritorioDTO {

    private Long id;
    private String local;
    private Double capacidade;

    public EscritorioDTO(Escritorio escritorio) {
        this.id = escritorio.getId();
        this.local = escritorio.getLocal();
        this.capacidade = escritorio.getCapacidade();
    }

    public static List<EscritorioDTO> convertList(List<Escritorio> escritorios) {
        return escritorios.stream().map(EscritorioDTO::new).collect(Collectors.toList());
    }
}
