package br.com.fcamara.hackatonapi.controller;

import br.com.fcamara.hackatonapi.dto.EscritorioDTO;
import br.com.fcamara.hackatonapi.dto.EstacaoDTO;
import br.com.fcamara.hackatonapi.exception.ErrorHandler;
import br.com.fcamara.hackatonapi.model.Escritorio;
import br.com.fcamara.hackatonapi.model.Estacao;
import br.com.fcamara.hackatonapi.service.escritorio.EscritorioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collection;
import java.util.Collections;

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

}
