package br.com.fcamara.agendalaranjaapi.controller;

import br.com.fcamara.agendalaranjaapi.dto.AgendamentoDTO;
import br.com.fcamara.agendalaranjaapi.dto.EscritorioDTO;
import br.com.fcamara.agendalaranjaapi.dto.EstacaoDTO;
import br.com.fcamara.agendalaranjaapi.exception.CapacityExceededException;
import br.com.fcamara.agendalaranjaapi.exception.ErrorHandler;
import br.com.fcamara.agendalaranjaapi.exception.NotFoundException;
import br.com.fcamara.agendalaranjaapi.model.Agendamento;
import br.com.fcamara.agendalaranjaapi.model.Escritorio;
import br.com.fcamara.agendalaranjaapi.model.Estacao;
import br.com.fcamara.agendalaranjaapi.service.escritorio.EscritorioService;
import br.com.fcamara.agendalaranjaapi.service.estacao.EstacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static br.com.fcamara.agendalaranjaapi.util.JsonConvertionUtils.asJsonString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EstacaoControllerTest {

    private static final String ESTACAO_API_URL_PATH = "/api/v1/estacao";
    private static final Long ESTACAO_VALID_ID = 1L;
    private static final Long ESTACAO_INVALID_ID = 2L;
    private Estacao estacaoBuilder() {
        return Estacao.builder()
                .id(1L)
                .nomeEstacao("Teste")
                .qtdLugares(10.0)
                .escritorio(Escritorio.builder().id(1L).local("Santos").capacidade(240.0).build())
                .build();
    }

    private EstacaoDTO estacaoDTOBuilder(Estacao estacao) {
        return EstacaoDTO.builder()
                .id(estacao.getId())
                .nomeEstacao(estacao.getNomeEstacao())
                .qtdLugares(estacao.getQtdLugares())
                .escritorioId(estacao.getEscritorio().getId())
                .build();
    }

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private EstacaoController controller;

    @Mock
    private EstacaoService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ErrorHandler())
                .build();
    }

    @Test
    void quandoGETParaListarEstacoesForChamadoSemPassarParametrosRetorneStatusCodeOk() throws Exception {

        mockMvc.perform(get(ESTACAO_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void quandoGETParaListarEstacoesForChamadoPassandoOParametroDeEscriotioIdRetorneStatusCodeOk() throws Exception {
        Estacao estacao = estacaoBuilder();

        mockMvc.perform(get(ESTACAO_API_URL_PATH)
                .param("escritorioId", estacao.getEscritorio().getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void quandoGETForChamadoPassandoUmIDValidoRetorneStatusCodeOk() throws Exception {
        Estacao estacao = estacaoBuilder();

        when(service.getStationById(ESTACAO_VALID_ID)).thenReturn(estacao);

        mockMvc.perform(get(ESTACAO_API_URL_PATH+"/"+ESTACAO_VALID_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void quandoGETForChamadoPassandoUmIdInvalidoRetorneStatusCodeNotFound() throws Exception {
        Estacao estacao = estacaoBuilder();

        when(service.getStationById(ESTACAO_INVALID_ID)).thenThrow(NotFoundException.class);

        mockMvc.perform(get(ESTACAO_API_URL_PATH+"/"+ESTACAO_INVALID_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    void quandoPOSTForChamadoEUmaEstacaoForCriadaRetorneStatusCodeCreated() throws Exception {
        Estacao estacao = estacaoBuilder();
        EstacaoDTO estacaoDTO = estacaoDTOBuilder(estacao);

        when(service.createStation(estacaoDTO)).thenReturn(estacao);

        mockMvc.perform(post(ESTACAO_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(estacaoDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void quandoPOSTForChamadoMasACapacidadeDoEscritorioJaEstiverNoMaximoRetorneStatusCodeCreated() throws Exception {
        Estacao estacao = estacaoBuilder();
        EstacaoDTO estacaoDTO = estacaoDTOBuilder(estacao);

        when(service.createStation(estacaoDTO)).thenThrow(CapacityExceededException.class);

        mockMvc.perform(post(ESTACAO_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(estacaoDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void quandoPUTForChamadoEUmaEstacaoForAtualizadoRetorneStatusCodeOk() throws Exception {
        Estacao estacao = estacaoBuilder();
        EstacaoDTO estacaoDTO = estacaoDTOBuilder(estacao);

        when(service.updateStation(ESTACAO_VALID_ID, estacaoDTO)).thenReturn(estacao);

        mockMvc.perform(put(ESTACAO_API_URL_PATH + "/" + ESTACAO_VALID_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(estacaoDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void quandoDELETEForChamadoEUmaEstacaoForDeletadoRetorneStatusCodeNoContent() throws Exception {

        doNothing().when(service).deleteStation(ESTACAO_VALID_ID);

        mockMvc.perform(delete(ESTACAO_API_URL_PATH + "/" + ESTACAO_VALID_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
