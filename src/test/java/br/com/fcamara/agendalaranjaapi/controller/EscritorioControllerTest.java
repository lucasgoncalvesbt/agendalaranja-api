package br.com.fcamara.agendalaranjaapi.controller;

import br.com.fcamara.agendalaranjaapi.dto.AgendamentoDTO;
import br.com.fcamara.agendalaranjaapi.dto.EscritorioDTO;
import br.com.fcamara.agendalaranjaapi.exception.ErrorHandler;
import br.com.fcamara.agendalaranjaapi.exception.NotFoundException;
import br.com.fcamara.agendalaranjaapi.model.Agendamento;
import br.com.fcamara.agendalaranjaapi.model.Escritorio;
import br.com.fcamara.agendalaranjaapi.service.escritorio.EscritorioService;
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

import static br.com.fcamara.agendalaranjaapi.util.JsonConvertionUtils.asJsonString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EscritorioControllerTest {

    private static final String ESCRITORIO_API_URL_PATH = "/api/v1/escritorio";
    private static final Long ESCRITORIO_VALID_ID = 1L;
    private static final Long ESCRITORIO_INVALID_ID = 2L;

    private Escritorio escritorioBuilder() {
        return Escritorio.builder()
                .id(1L)
                .local("Santos")
                .capacidade(600.0)
                .build();
    }

    private EscritorioDTO escritorioDTOBuilder(Escritorio escritorio) {
        return EscritorioDTO.builder()
                .id(escritorio.getId())
                .local(escritorio.getLocal())
                .capacidade(escritorio.getCapacidade())
                .build();
    }

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private EscritorioController controller;

    @Mock
    private EscritorioService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ErrorHandler())
                .build();
    }

    @Test
    void quandoGETForChamadoParaListarEscritoriosRetorneStatusCodeOk() throws Exception {

        mockMvc.perform(get(ESCRITORIO_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void quandoGETForChamadoPassandoUmIDValidoRetorneStatusCodeOk() throws Exception {
        Escritorio escritorio = escritorioBuilder();

        when(service.getDeskById(ESCRITORIO_VALID_ID)).thenReturn(escritorio);

        mockMvc.perform(get(ESCRITORIO_API_URL_PATH+"/"+ESCRITORIO_VALID_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void quandoGETForChamadoPassandoUmIdInvalidoRetorneStatusCodeNotFound() throws Exception {
        Escritorio escritorio = escritorioBuilder();

        when(service.getDeskById(ESCRITORIO_INVALID_ID)).thenThrow(NotFoundException.class);

        mockMvc.perform(get(ESCRITORIO_API_URL_PATH+"/"+ESCRITORIO_INVALID_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


    }

    @Test
    void quandoPOSTForChamadoEUmEscritorioForCriadoRetorneStatusCodeCreated() throws Exception {
        Escritorio escritorio = escritorioBuilder();
        EscritorioDTO escritorioDTO = escritorioDTOBuilder(escritorio);

        when(service.registrarEscritorio(escritorioDTO)).thenReturn(escritorio);

        mockMvc.perform(post(ESCRITORIO_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(escritorioDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void quandoPUTForChamadoEUmEscritorioForAtualizadoRetorneStatusCodeOk() throws Exception {
        Escritorio escritorio = escritorioBuilder();
        EscritorioDTO escritorioDTO = escritorioDTOBuilder(escritorio);

        when(service.updateDesk(escritorioDTO.getId(),escritorioDTO)).thenReturn(escritorio);

        mockMvc.perform(put(ESCRITORIO_API_URL_PATH + "/" + ESCRITORIO_VALID_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(escritorioDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void quandoDELETEForChamadoEUmEscritorioForDeletadoRetorneStatusCodeNoContent() throws Exception {

        doNothing().when(service).deleteDesk(ESCRITORIO_VALID_ID);

        mockMvc.perform(delete(ESCRITORIO_API_URL_PATH + "/" + ESCRITORIO_VALID_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
