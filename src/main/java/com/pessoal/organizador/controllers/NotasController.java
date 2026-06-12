package com.pessoal.organizador.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoal.organizador.DTOs.AtualizaDTO;
import com.pessoal.organizador.DTOs.ListaNotasDTO;
import com.pessoal.organizador.DTOs.NovaNotaDTO;
import com.pessoal.organizador.models.Nota;
import com.pessoal.organizador.repositorys.NotasRepository;

import jakarta.transaction.Transactional;




@RestController
@RequestMapping("notas")
public class NotasController {
    
    @Autowired
    private NotasRepository repository;

    private Nota nota;

    @GetMapping
    public Page<ListaNotasDTO> listaNotas(@PageableDefault(size=50, sort={"title"}) Pageable paginacao) {
        return repository.findByExpiredFalse(paginacao)
            .map(nota -> new ListaNotasDTO(nota.getId(), nota.getTitle(), nota.getObs(), nota.getContent(), nota.getExpireAt(), nota.getExpired(), nota.getNoteType()));
    }

    @GetMapping("expiradas")
    public List<ListaNotasDTO> notasExpiradas() {
        return repository.findByExpireAtBefore(LocalDateTime.now());
    }
    
    

    @PostMapping
    @Transactional
    public ResponseEntity<Nota> novaNota(@RequestBody NovaNotaDTO dados) {
        repository.save(new Nota(dados));
        return ResponseEntity.ok().build();
    
    }

    @PutMapping("complete/{id}")
    @Transactional
    public ResponseEntity<Nota> completaNota(@PathVariable Long id) {
        var entity = repository.getReferenceById(id);
        entity.setCompleted(true);
        repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("editar/{id}")
    @Transactional
    public ResponseEntity<Nota> atualizaNota(@PathVariable Long id, @RequestBody AtualizaDTO e) {
        var entity = repository.getReferenceById(id);
        entity.setTitle(e.title());
        entity.setContent(e.content());
        entity.setObs(e.obs());
        repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deleteNota(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
