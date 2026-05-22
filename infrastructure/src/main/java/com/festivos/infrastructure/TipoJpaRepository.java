package com.festivos.infrastructure;

import com.festivos.domain.Tipo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoJpaRepository extends JpaRepository<Tipo, Long> {
  Optional<Tipo> findByTipo(String tipo);
}

