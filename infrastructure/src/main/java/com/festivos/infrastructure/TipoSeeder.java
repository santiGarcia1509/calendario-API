package com.festivos.infrastructure;

import com.festivos.domain.Tipo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TipoSeeder implements CommandLineRunner {

  private final TipoJpaRepository tipoJpaRepository;

  public TipoSeeder(TipoJpaRepository tipoJpaRepository) {
    this.tipoJpaRepository = tipoJpaRepository;
  }

  @Override
  @Transactional
  public void run(String... args) {
    crearSiNoExiste(1L, "Dia laboral");
    crearSiNoExiste(2L, "Fin de Semana");
    crearSiNoExiste(3L, "Dia festivo");
  }

  private void crearSiNoExiste(Long id, String tipoNombre) {
    boolean existePorId = tipoJpaRepository.findById(id).isPresent();
    boolean existePorNombre = tipoJpaRepository.findByTipo(tipoNombre).isPresent();
    if (existePorId || existePorNombre) {
      return;
    }
    Tipo tipo = new Tipo();
    tipo.setId(id);
    tipo.setTipo(tipoNombre);
    tipoJpaRepository.save(tipo);
  }
}

