package com.pessoal.organizador.DTOs;

import java.time.LocalDateTime;

public record ListaNotasDTO(
    Long id,
    String title,
    String content,
    String obs,
    LocalDateTime expire



) {
    
}
