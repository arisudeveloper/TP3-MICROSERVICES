package br.edu.infnet.tp3microservices.controller;

import br.edu.infnet.tp3microservices.dto.LoginRequest;
import br.edu.infnet.tp3microservices.dto.LoginResponse;
import br.edu.infnet.tp3microservices.dto.RefreshTokenRequest;
import br.edu.infnet.tp3microservices.dto.UsuarioRequest;
import br.edu.infnet.tp3microservices.model.Usuario;
import br.edu.infnet.tp3microservices.service.JwtToken;
import br.edu.infnet.tp3microservices.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final JwtToken jwtService;

    public UsuarioController(UsuarioService service, JwtToken jwtService) {
        this.service = service;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<Long> cadastrar(@Valid @RequestBody UsuarioRequest request) {
        Usuario usuario = service.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario.getId());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Usuario usuario = this.service.autenticar(request);
        String token = jwtService.gerarToken(usuario);
        String refreshToken = jwtService.gerarRefreshToken(usuario);
        return ResponseEntity.ok(new LoginResponse(token, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshTokenRequest request) {
        if (!jwtService.validarToken(request.getRefreshToken())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh Token invalido ou expirado");
        }
        String email = jwtService.obterEmailDoToken(request.getRefreshToken());
        Usuario usuario = service.buscarPorEmail(email);
        String novoToken = jwtService.gerarToken(usuario);
        String novoRefreshToken = jwtService.gerarRefreshToken(usuario);
        return ResponseEntity.ok(new LoginResponse(novoToken, novoRefreshToken));
    }
}