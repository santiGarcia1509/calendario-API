package com.festivos.infrastructure;

import com.festivos.application.FestivoClientService;
import com.festivos.application.FestivoDTO;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class FestivoApiClient implements FestivoClientService {

  private final RestTemplate restTemplate;

  public FestivoApiClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<FestivoDTO> obtenerFestivos(int anio) {
    try {
      String url = "http://localhost:8080/api/festivos/obtener/" + anio;
      ResponseEntity<FestivoDTO[]> response = restTemplate.getForEntity(url, FestivoDTO[].class);
      FestivoDTO[] body = response.getBody();
      if (body == null) {
        return Collections.emptyList();
      }
      return Arrays.asList(body);
    } catch (RestClientException ex) {
      throw new RuntimeException(
          "No se pudo conectar con la API de festivos en http://localhost:8080. Verifica que esté activa en el puerto 8080.",
          ex
      );
    }
  }
}

