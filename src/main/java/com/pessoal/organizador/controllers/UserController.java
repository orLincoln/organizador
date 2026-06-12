package com.pessoal.organizador.controllers;


import com.pessoal.organizador.DTOs.DadosUsuarioLogin;
import com.pessoal.organizador.DTOs.DadosUsuarioSignup;
import com.pessoal.organizador.DTOs.LoginResponseDTO;
import com.pessoal.organizador.models.User;
import com.pessoal.organizador.repositorys.UserRepository;
import com.pessoal.organizador.services.AuthorizationService;
import com.pessoal.organizador.services.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository repository;

    @Autowired
    TokenService tokenService;




    @PostMapping("entrar")
    @Transactional
    public ResponseEntity login(@RequestBody DadosUsuarioLogin dados){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("cadastro")
    @Transactional
    public ResponseEntity signup(@RequestBody DadosUsuarioSignup dados){
        if(repository.findByLogin(dados.login()) != null){
            return ResponseEntity.badRequest().build();
        }else{
            String encryptedPassword = new BCryptPasswordEncoder().encode(dados.senha());
            User newUser = new User(dados.login(), dados.nome(), encryptedPassword);
            this.repository.save(newUser);
            return ResponseEntity.ok().build();
        }
    }








}
