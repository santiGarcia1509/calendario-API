package com.festivos.domain;

import java.util.Optional;

public interface TipoRepository {
  Optional<Tipo> findByTipo(String tipo);
  Tipo save(Tipo tipo);
}

