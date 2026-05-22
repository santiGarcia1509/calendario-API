package com.festivos.application;

import java.util.List;

public interface FestivoClientService {
  List<FestivoDTO> obtenerFestivos(int anio);
}

