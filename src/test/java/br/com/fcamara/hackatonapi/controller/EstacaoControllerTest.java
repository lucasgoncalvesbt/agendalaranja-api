package br.com.fcamara.hackatonapi.controller;

import br.com.fcamara.hackatonapi.dto.EstacaoDTO;
import br.com.fcamara.hackatonapi.exception.ErrorHandler;
import br.com.fcamara.hackatonapi.model.Escritorio;
import br.com.fcamara.hackatonapi.model.Estacao;
import br.com.fcamara.hackatonapi.service.escritorio.EscritorioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
