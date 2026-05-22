package com.festivos.presentation;

import com.festivos.application.CalendarioService;
import com.festivos.application.FestivoDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/festivos")
public class FestivoController {

  private final CalendarioService calendarioService;

  public FestivoController(CalendarioService calendarioService) {
    this.calendarioService = calendarioService;
  }

  @GetMapping("/obtener/{anio}")
  public ResponseEntity<List<FestivoDTO>> obtener(@PathVariable int anio) {
    return ResponseEntity.ok(calendarioService.listarFestivos(anio));
  }
}

