package com.festivos.application;

import java.util.List;

public interface CalendarioService {
  List<FestivoDTO> listarFestivos(int anio);
  boolean generarCalendario(int anio);
  List<CalendarioDiaDTO> listarCalendario(int anio);
}

