package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.AutenticacaoDTO;
import med.voll.api.infra.security.TokenDTO;
import med.voll.api.model.usuario.Usuario;
import med.voll.api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
        @Autowired
        private AuthenticationManager manager;
        @Autowired
        private TokenService tokenService;
        @PostMapping
        public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoDTO data) {
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
            var authentication =  manager.authenticate(authenticationToken);
            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

            return ResponseEntity.ok(new TokenDTO(tokenJWT));
        }
}
