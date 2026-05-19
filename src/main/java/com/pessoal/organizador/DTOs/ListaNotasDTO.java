package com.pessoal.organizador.DTOs;

import java.time.LocalDateTime;

import com.pessoal.organizador.enums.NoteType;

public record ListaNotasDTO(
    Long id,
    String title,
    String content,
    String obs,
    LocalDateTime expireAt,
    boolean expired,
    NoteType noteType



) {
    
}
