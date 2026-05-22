package com.festivos.infrastructure;

import com.festivos.domain.Calendario;
import com.festivos.domain.CalendarioRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarioRepositoryImpl implements CalendarioRepository {

  private final CalendarioJpaRepository calendarioJpaRepository;

  public CalendarioRepositoryImpl(CalendarioJpaRepository calendarioJpaRepository) {
    this.calendarioJpaRepository = calendarioJpaRepository;
  }

  @Override
  public List<Calendario> findByFechaBetween(LocalDate inicio, LocalDate fin) {
    return calendarioJpaRepository.findByFechaBetween(inicio, fin);
  }

  @Override
  public void deleteByFechaBetween(LocalDate inicio, LocalDate fin) {
    calendarioJpaRepository.deleteByFechaBetween(inicio, fin);
  }

  @Override
  public Calendario save(Calendario calendario) {
    return calendarioJpaRepository.save(calendario);
  }
}

