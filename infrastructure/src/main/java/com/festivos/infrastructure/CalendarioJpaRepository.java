package com.festivos.infrastructure;

import com.festivos.domain.Calendario;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarioJpaRepository extends JpaRepository<Calendario, Long> {
  List<Calendario> findByFechaBetween(LocalDate inicio, LocalDate fin);
  void deleteByFechaBetween(LocalDate inicio, LocalDate fin);
}

