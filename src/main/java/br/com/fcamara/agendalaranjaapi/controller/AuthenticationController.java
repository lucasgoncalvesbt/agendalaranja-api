package br.com.fcamara.agendalaranjaapi.controller;

import br.com.fcamara.agendalaranjaapi.dto.CadastroDTO;
import br.com.fcamara.agendalaranjaapi.dto.ContaCriadaDTO;
import br.com.fcamara.agendalaranjaapi.dto.LoginDTO;
import br.com.fcamara.agendalaranjaapi.dto.TokenDTO;
import br.com.fcamara.agendalaranjaapi.exception.EmailAlreadyHasAnAccountException;
import br.com.fcamara.agendalaranjaapi.model.Usuario;
import br.com.fcamara.agendalaranjaapi.repository.UserRepository;
import br.com.fcamara.agendalaranjaapi.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/sign")
    public ResponseEntity<TokenDTO> auth(@RequestBody @Validated LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        String token = tokenService.generateToken(authentication);

        return ResponseEntity.ok(TokenDTO.builder().type("Bearer").token(token).build());

    }

    @PostMapping("/signout")
    public ResponseEntity<ContaCriadaDTO> signout(@RequestBody @Validated CadastroDTO cadastroDTO) {

        if(userRepository.findByEmail(cadastroDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyHasAnAccountException(cadastroDTO.getEmail());
        }

        Usuario usuario = Usuario.builder()
                .email(cadastroDTO.getEmail())
                .nome(cadastroDTO.getNome())
                .sobrenome(cadastroDTO.getSobrenome())
                .pass(new BCryptPasswordEncoder().encode(cadastroDTO.getSenha()))
                .build();

        userRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ContaCriadaDTO.builder()
                        .mensagem("Conta com email " + cadastroDTO.getEmail() + " criada com sucesso!").build());
    }


}
