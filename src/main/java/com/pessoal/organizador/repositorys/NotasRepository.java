package com.pessoal.organizador.repositorys;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoal.organizador.interfaces.Nota;


public interface NotasRepository extends JpaRepository<Nota, Long> {
    
}
