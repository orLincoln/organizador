package com.pessoal.organizador.DTOs;

import java.time.LocalDateTime;

import com.pessoal.organizador.interfaces.Nota;

import jakarta.validation.constraints.NotBlank;

public record NovaNotaDTO(

    @NotBlank
    String title,

    @NotBlank
    String content,
 
    String obs,

    LocalDateTime expire

) {


    public record ListaNotasDTO(String title, String content, String obs, LocalDateTime expire) {
    public ListaNotasDTO(Nota nota) {
        this(nota.getTitle(), nota.getContent(), nota.getObs(), nota.getExpire());
    }
}
}
