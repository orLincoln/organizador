package com.pessoal.organizador.DTOs;

import java.time.LocalDateTime;

import com.pessoal.organizador.enums.NoteType;
import com.pessoal.organizador.interfaces.Nota;

import jakarta.validation.constraints.NotBlank;

public record NovaNotaDTO(

    @NotBlank
    String title,

    @NotBlank
    String content,
 
    String obs,

    LocalDateTime expireAt,

    @NotBlank
    NoteType noteType

) {


    public record ListaNotasDTO(String title, String content, String obs, LocalDateTime expireAt) {
    public ListaNotasDTO(Nota nota) {
        this(nota.getTitle(), nota.getContent(), nota.getObs(), nota.getExpireAt());
    }
}
}
