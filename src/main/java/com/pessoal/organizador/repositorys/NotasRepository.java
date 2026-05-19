package com.pessoal.organizador.repositorys;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pessoal.organizador.DTOs.ListaNotasDTO;
import com.pessoal.organizador.interfaces.Nota;


public interface NotasRepository extends JpaRepository<Nota, Long> {
    List<ListaNotasDTO> findByExpireAtBefore(LocalDateTime dataHora);
    Page<Nota> findByExpiredFalse(Pageable paginacao);

	@Modifying
	@Query("UPDATE Nota n SET n.expired = true WHERE n.expireAt < :agora AND n.expired = false")
	void marcarExpiradas(@Param("agora") LocalDateTime agora);


    
}
