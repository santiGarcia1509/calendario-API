package com.festivos.domain;

import java.time.LocalDate;
import java.util.List;

public interface CalendarioRepository {
  List<Calendario> findByFechaBetween(LocalDate inicio, LocalDate fin);
  void deleteByFechaBetween(LocalDate inicio, LocalDate fin);
  Calendario save(Calendario calendario);
}

