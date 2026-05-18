package com.pessoal.organizador.interfaces;

import java.time.LocalDateTime;

import com.pessoal.organizador.DTOs.NovaNotaDTO;
import com.pessoal.organizador.enums.NoteType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name="notas")
@Entity(name = "nota")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Nota {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    private String title;

    private String content;

    private String obs;

    private Boolean completed;

    private NoteType type;



    private int ano;
    private int mes;
    private int dia;
    private int hora;
    private int minuto;
    
    private LocalDateTime expire;


    public void setExpire(LocalDateTime expire) {
        this.expire = expire;
    }

    public Nota(NovaNotaDTO d){
        this.title = d.title();
        this.content = d.content(); 
        this.obs = d.obs();

        //expira em 30 dias
        this.expire = LocalDateTime.now().plusDays(30);

        //salva data do post
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

    public LocalDateTime getExpire() {
        return expire;
    }

    public Long getId() {
        return id;
    }


}
