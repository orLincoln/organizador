package com.pessoal.organizador.interfaces;

import java.time.LocalDateTime;

import com.pessoal.organizador.DTOs.NovaNotaDTO;
import com.pessoal.organizador.enums.NoteType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name="notas")
@Entity(name = "Nota")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Nota {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    private String title;

    @Column(length=512)
    private String content;

    @Column(length=256)
    private String obs;

    private Boolean completed;

    private LocalDateTime expireAt;
    private boolean expired;

    private NoteType noteType;


    private int ano;
    private int mes;
    private int dia;
    private int hora;
    private int minuto;
    


    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public Nota(NovaNotaDTO d){
        this.title = d.title();
        this.content = d.content(); 
        this.obs = d.obs();
        this.expired = false;
        this.completed = false;
        this.noteType = d.noteType();

        //expira em 30 dias por padrão, caso o usuário não defina uma data de expiração
        this.expireAt = d.expireAt() != null ? d.expireAt() : LocalDateTime.now().plusDays(30);

        //salva data do postamento da nota
        LocalDateTime agora = LocalDateTime.now();
        this.ano    = agora.getYear();
        this.mes    = agora.getMonthValue();
        this.dia    = agora.getDayOfMonth();
        this.hora   = agora.getHour();
        this.minuto = agora.getMinute();

    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getObs() {
        return obs;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public boolean getExpired() {
        return expired;
    }

    public Long getId() {
        return id;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public NoteType getNoteType() {
        return noteType;
    }

    public void setNoteType(NoteType noteType) {
        this.noteType = noteType;
    }


}
