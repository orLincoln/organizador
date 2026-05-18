package com.pessoal.organizador.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
import com.pessoal.organizador.interfaces.Nota;
import com.pessoal.organizador.repositorys.NotasRepository;

import jakarta.transaction.Transactional;





@RestController
@RequestMapping("notas")
public class NotasController {
    
    @Autowired
    private NotasRepository repository;

    private Nota nota;

    @GetMapping
    public Page<ListaNotasDTO> getMethodName(@PageableDefault(size=10, sort={"title"}) Pageable paginacao) {
        return repository.findAll(paginacao)
            .map(nota -> new ListaNotasDTO(nota.getId(), nota.getTitle(), nota.getObs(), nota.getContent(), nota.getExpire()));
    }
    

    @PostMapping
    @Transactional
    public ResponseEntity<Nota> postMethodName(@RequestBody NovaNotaDTO dados) {
        repository.save(new Nota(dados));
        return ResponseEntity.ok().build();
    
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Nota> putMethodName(@PathVariable Long id) {
        var entity = repository.getReferenceById(id);
        entity.setCompleted(true);
        repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Nota> putMethodName(@PathVariable Long id, @RequestBody AtualizaDTO e) {
        var entity = repository.getReferenceById(id);
        entity.setTitle(e.title());
        entity.setContent(e.content());
        entity.setObs(e.obs());
        repository.save(entity);
        return ResponseEntity.ok(entity);
    }
    
}
