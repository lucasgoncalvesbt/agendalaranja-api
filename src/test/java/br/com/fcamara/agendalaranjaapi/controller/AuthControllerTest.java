package br.com.fcamara.agendalaranjaapi.controller;

import br.com.fcamara.agendalaranjaapi.dto.CadastroDTO;
import br.com.fcamara.agendalaranjaapi.dto.ContaCriadaDTO;
import br.com.fcamara.agendalaranjaapi.dto.LoginDTO;
import br.com.fcamara.agendalaranjaapi.dto.TokenDTO;
import br.com.fcamara.agendalaranjaapi.exception.EmailAlreadyHasAnAccountException;
import br.com.fcamara.agendalaranjaapi.exception.ErrorHandler;
import br.com.fcamara.agendalaranjaapi.model.Usuario;
import br.com.fcamara.agendalaranjaapi.repository.UserRepository;
import br.com.fcamara.agendalaranjaapi.service.escritorio.EscritorioService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    private static final String AUTH_API_URL_PATH = "/api/v1/auth/";

    private CadastroDTO cadastroDTOBuilder() {
        return CadastroDTO.builder()
                .email("sicrano@fcamara.com")
                .nome("fulano")
                .sobrenome("de tal")
                .senha("1234")
                .build();
    }

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AuthenticationController controller;

    @Mock
    private UserRepository repository;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ErrorHandler())
                .build();
    }

    @Test
    void quandoPOSTForChamadoEUmUsuarioForCriadoRetorneStatusCodeOk() throws Exception {
        CadastroDTO cadastroDTO = cadastroDTOBuilder();

        mockMvc.perform(post(AUTH_API_URL_PATH+"signout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(cadastroDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void quandoPOSTForChamadoParaCriarUmUsuarioMasJaTiverUmUsuarioComOMesmoEmailRetorneStatusCodeBadRequest() throws Exception {
        CadastroDTO cadastroDTO = cadastroDTOBuilder();
        Usuario usuario = Usuario.builder().id(1).email("sicrano@fcamara").build();

        when(repository.findByEmail(cadastroDTO.getEmail())).thenReturn(java.util.Optional.ofNullable(usuario));

        mockMvc.perform(post(AUTH_API_URL_PATH+"signout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(cadastroDTO)))
                .andExpect(status().isBadRequest());
    }

}
