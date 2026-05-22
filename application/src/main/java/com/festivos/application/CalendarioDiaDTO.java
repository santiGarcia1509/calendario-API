package com.festivos.application;

import com.festivos.domain.Tipo;
import java.time.OffsetDateTime;

public class CalendarioDiaDTO {
  private Long id;
  private OffsetDateTime fecha;
  private Tipo tipo;
  private String descripcion;

  public CalendarioDiaDTO() {
  }

  public CalendarioDiaDTO(Long id, OffsetDateTime fecha, Tipo tipo, String descripcion) {
    this.id = id;
    this.fecha = fecha;
    this.tipo = tipo;
    this.descripcion = descripcion;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OffsetDateTime getFecha() {
    return fecha;
  }

  public void setFecha(OffsetDateTime fecha) {
    this.fecha = fecha;
  }

  public Tipo getTipo() {
    return tipo;
  }

  public void setTipo(Tipo tipo) {
    this.tipo = tipo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}

