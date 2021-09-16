package br.com.fcamara.agendalaranjaapi.dto;

import br.com.fcamara.agendalaranjaapi.model.Estacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstacaoDTO {

    private Long id;
    private Double qtdLugares;
    private String nomeEstacao;
    private Long escritorioId;

    public EstacaoDTO(Estacao estacao) {
        this.id = estacao.getId();
        this.qtdLugares = estacao.getQtdLugares();
        this.nomeEstacao = estacao.getNomeEstacao();
        this.escritorioId = estacao.getEscritorio().getId();
    }

    public static List<EstacaoDTO> convertList(List<Estacao> estacoes) {
        return estacoes.stream().map(EstacaoDTO::new).collect(Collectors.toList());
    }
}
