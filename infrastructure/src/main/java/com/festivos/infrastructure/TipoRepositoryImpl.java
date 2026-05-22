package com.festivos.infrastructure;

import com.festivos.domain.Tipo;
import com.festivos.domain.TipoRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class TipoRepositoryImpl implements TipoRepository {

  private final TipoJpaRepository tipoJpaRepository;

  public TipoRepositoryImpl(TipoJpaRepository tipoJpaRepository) {
    this.tipoJpaRepository = tipoJpaRepository;
  }

  @Override
  public Optional<Tipo> findByTipo(String tipo) {
    return tipoJpaRepository.findByTipo(tipo);
  }

  @Override
  public Tipo save(Tipo tipo) {
    return tipoJpaRepository.save(tipo);
  }
}

