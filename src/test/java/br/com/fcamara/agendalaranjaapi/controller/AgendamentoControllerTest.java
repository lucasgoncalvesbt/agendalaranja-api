package br.com.fcamara.agendalaranjaapi.controller;

import br.com.fcamara.agendalaranjaapi.dto.AgendamentoDTO;
import br.com.fcamara.agendalaranjaapi.exception.*;
import br.com.fcamara.agendalaranjaapi.model.Agendamento;
import br.com.fcamara.agendalaranjaapi.model.Escritorio;
import br.com.fcamara.agendalaranjaapi.model.Estacao;
import br.com.fcamara.agendalaranjaapi.service.agendamento.AgendamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static br.com.fcamara.agendalaranjaapi.util.JsonConvertionUtils.asJsonString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
public class AgendamentoControllerTest {

    private static final String AGENDAMENTO_API_URL_PATH = "/api/v1/agendamento";
    private static final UUID AGENDAMENTO_VALID_ID = UUID.randomUUID();
    private static final UUID AGENDAMENTO_INVALID_ID = UUID.randomUUID();
    private Agendamento agendamentoBuilder() {
        return Agendamento.builder()
                .id(AGENDAMENTO_VALID_ID)
                .nomeConsultor("Fulano X")
                .emailConsultor("email@fcamara.com")
                .estacao(Estacao.builder().id(1L).escritorio(Escritorio.builder().id(1L).build()).build())
                .build();
    }

    private AgendamentoDTO agendamentoDTOBuilder(Agendamento agendamento) {
        return AgendamentoDTO.builder()
                .id(agendamento.getId())
                .nomeConsultor(agendamento.getNomeConsultor())
                .emailConsultor(agendamento.getEmailConsultor())
                .estacaoId(agendamento.getEstacao().getId())
                .build();
    }

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AgendamentoController controller;

    @Mock
    private AgendamentoService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ErrorHandler())
                .build();
    }

    @Test
    void quandoGETParaListarAgendamentosForChamadoPassandoOParametroDeEmailDoConsultorRetorneStatusCodeOk() throws Exception {
        Agendamento agendamento = agendamentoBuilder();

        mockMvc.perform(get(AGENDAMENTO_API_URL_PATH)
                .param("emailConsultor",agendamento.getEmailConsultor())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void quandoGETParaListarAgendamentosForChamadoPassandoOParametroDeEstacaoIdRetorneStatusCodeOk() throws Exception {
        Agendamento agendamento = agendamentoBuilder();

        mockMvc.perform(get(AGENDAMENTO_API_URL_PATH)
                .param("estacaoId",agendamento.getEstacao().getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void quandoGETForChamadoPassandoUmIDValidoRetorneStatusCodeOk() throws Exception {
        Agendamento agendamento = agendamentoBuilder();

        when(service.getSchedulingById(AGENDAMENTO_VALID_ID)).thenReturn(agendamento);

        mockMvc.perform(get(AGENDAMENTO_API_URL_PATH+"/"+AGENDAMENTO_VALID_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void quandoGETForChamadoPassandoUmIdInvalidoRetorneStatusCodeNotFound() throws Exception {
        Agendamento agendamento = agendamentoBuilder();

        when(service.getSchedulingById(AGENDAMENTO_INVALID_ID)).thenThrow(NotFoundException.class);

        mockMvc.perform(get(AGENDAMENTO_API_URL_PATH+"/"+AGENDAMENTO_INVALID_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    void quandoPOSTForChamadoEUmAgendamentoForCriadaRetorneStatusCodeCreated() throws Exception {
        Agendamento agendamento = agendamentoBuilder();
        AgendamentoDTO agendamentoDTO = agendamentoDTOBuilder(agendamento);

        when(service.createScheduling(agendamentoDTO)).thenReturn(agendamento);

        mockMvc.perform(post(AGENDAMENTO_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(agendamentoDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void quandoPOSTForChamadoEUmAgendamentoJaTenhaSidoCriadoParaEsteDiaRetorneStatusCodeBadRequest() throws Exception {
        Agendamento agendamento = agendamentoBuilder();
        AgendamentoDTO agendamentoDTO = agendamentoDTOBuilder(agendamento);

        when(service.createScheduling(agendamentoDTO)).thenThrow(EmailAlreadyScheduledForThisDayException.class);

        mockMvc.perform(post(AGENDAMENTO_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(agendamentoDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void quandoPOSTForChamadoEOEmailNaoPertencerAoUsuarioLogadoRetorneStatusCodeUnauthorized() throws Exception {
        Agendamento agendamento = agendamentoBuilder();
        AgendamentoDTO agendamentoDTO = agendamentoDTOBuilder(agendamento);

        when(service.createScheduling(agendamentoDTO)).thenThrow(UserDoesNotHavePermissionException.class);

        mockMvc.perform(post(AGENDAMENTO_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(agendamentoDTO)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void quandoPOSTForChamadoEONumeroDeAgendamentosDaEstacaoEscolhidaJaEstiverNoMaximoRetorneStatusBadRequest() throws Exception {
        Agendamento agendamento = agendamentoBuilder();
        AgendamentoDTO agendamentoDTO = agendamentoDTOBuilder(agendamento);

        when(service.createScheduling(agendamentoDTO)).thenThrow(SchedulingExceededException.class);

        mockMvc.perform(post(AGENDAMENTO_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(agendamentoDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void quandoPUTForChamadoEUmAgendamentoForAtualizadoRetorneStatusCodeOk() throws Exception {
        Agendamento agendamento = agendamentoBuilder();
        AgendamentoDTO agendamentoDTO = agendamentoDTOBuilder(agendamento);

        when(service.updateScheduling(AGENDAMENTO_VALID_ID, agendamentoDTO)).thenReturn(agendamento);

        mockMvc.perform(put(AGENDAMENTO_API_URL_PATH + "/" + AGENDAMENTO_VALID_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(agendamentoDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void quandoDELETEForChamadoEUmAgendamentoForDeletadoRetorneStatusCodeNoContent() throws Exception {

        doNothing().when(service).deleteScheduling(AGENDAMENTO_VALID_ID);

        mockMvc.perform(delete(AGENDAMENTO_API_URL_PATH + "/" + AGENDAMENTO_VALID_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
