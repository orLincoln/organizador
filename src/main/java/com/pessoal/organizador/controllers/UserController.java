package com.pessoal.organizador.controllers;


import com.pessoal.organizador.DTOs.DadosUsuarioLogin;
import com.pessoal.organizador.models.User;
import com.pessoal.organizador.repositorys.UserRepository;
import com.pessoal.organizador.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("auth")
public class UserController {

    AuthorizationService authorizationService;

    @Autowired
    UserRepository repository;

    @PostMapping("entrar")
    public ResponseEntity login(@RequestBody DadosUsuarioLogin dados){
        if(dados != null){
            repository.save(new User(dados));
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }








}
