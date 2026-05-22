package com.festivos.presentation;

import com.festivos.application.CalendarioDiaDTO;
import com.festivos.application.CalendarioService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioController {

  private final CalendarioService calendarioService;

  public CalendarioController(CalendarioService calendarioService) {
    this.calendarioService = calendarioService;
  }

  @GetMapping("/generar/{anio}")
  public ResponseEntity<Boolean> generar(@PathVariable("anio") int anio) {
      return ResponseEntity.ok(calendarioService.generarCalendario(anio));
  }

  @GetMapping("/listar/{anio}")
  public ResponseEntity<List<CalendarioDiaDTO>> listar(@PathVariable("anio") int anio) {
      return ResponseEntity.ok(calendarioService.listarCalendario(anio));
  }
}

