package sisgerim.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sisgerim.backend.domain.pessoa.corretor.AuthenticationRequestDTO;
import sisgerim.backend.domain.pessoa.corretor.Corretor;
import sisgerim.backend.services.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationRequestDTO data){
        UsernamePasswordAuthenticationToken emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        Authentication auth = this.authenticationManager.authenticate(emailPassword);
        String token = tokenService.generateToken((Corretor) auth.getPrincipal());
        return new ResponseEntity<String>(token, HttpStatus.ACCEPTED);
    }
}
