package com.festivos.infrastructure;

import com.festivos.application.CalendarioDiaDTO;
import com.festivos.application.CalendarioService;
import com.festivos.application.FestivoDTO;
import com.festivos.domain.Calendario;
import com.festivos.domain.CalendarioRepository;
import com.festivos.domain.Tipo;
import com.festivos.domain.TipoRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalendarioServiceImpl implements CalendarioService {

  private static final String TIPO_DIA_LABORAL = "Dia laboral";
  private static final String TIPO_FIN_DE_SEMANA = "Fin de Semana";
  private static final String TIPO_DIA_FESTIVO = "Dia festivo";

  private final TipoRepository tipoRepository;
  private final CalendarioRepository calendarioRepository;

  public CalendarioServiceImpl(
      TipoRepository tipoRepository,
      CalendarioRepository calendarioRepository
  ) {
    this.tipoRepository = tipoRepository;
    this.calendarioRepository = calendarioRepository;
  }

  @Override
  public List<FestivoDTO> listarFestivos(int anio) {
    return List.of();
  }

  @Override
  @Transactional
  public boolean generarCalendario(int anio) {

    try {

      // Temporalmente sin consumir API externa
      List<FestivoDTO> festivos = List.of();

      Set<LocalDate> fechasFestivas = festivos.stream()
          .map(FestivoDTO::fecha)
          .map(LocalDate::parse)
          .collect(Collectors.toSet());

      LocalDate inicio = LocalDate.of(anio, 1, 1);
      LocalDate fin = LocalDate.of(anio, 12, 31);

      calendarioRepository.deleteByFechaBetween(inicio, fin);

      LocalDate fecha = inicio;

      while (!fecha.isAfter(fin)) {

        String tipoNombre = determinarTipo(fecha, fechasFestivas);

        Tipo tipo = tipoRepository.findByTipo(tipoNombre)
            .orElseGet(() -> tipoRepository.save(new Tipo(null, tipoNombre)));

        Calendario calendario = new Calendario();

        calendario.setFecha(fecha);
        calendario.setTipo(tipo);
        calendario.setDescripcion(nombreDiaEnEspanol(fecha.getDayOfWeek()));

        calendarioRepository.save(calendario);

        fecha = fecha.plusDays(1);
      }

      return true;

    } catch (Exception ex) {

      ex.printStackTrace();
      return false;
    }
  }

  @Override
  public List<CalendarioDiaDTO> listarCalendario(int anio) {

    LocalDate inicio = LocalDate.of(anio, 1, 1);
    LocalDate fin = LocalDate.of(anio, 12, 31);

    return calendarioRepository.findByFechaBetween(inicio, fin)
        .stream()
        .sorted(Comparator.comparing(Calendario::getFecha))
        .map(this::toDto)
        .collect(Collectors.toList());
  }

  private String determinarTipo(
      LocalDate fecha,
      Set<LocalDate> fechasFestivas
  ) {

    if (fechasFestivas.contains(fecha)) {
      return TIPO_DIA_FESTIVO;
    }

    DayOfWeek dow = fecha.getDayOfWeek();

    if (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) {
      return TIPO_FIN_DE_SEMANA;
    }

    return TIPO_DIA_LABORAL;
  }

  private String nombreDiaEnEspanol(DayOfWeek dayOfWeek) {

    return switch (dayOfWeek) {

      case MONDAY -> "Lunes";
      case TUESDAY -> "Martes";
      case WEDNESDAY -> "Miércoles";
      case THURSDAY -> "Jueves";
      case FRIDAY -> "Viernes";
      case SATURDAY -> "Sábado";
      case SUNDAY -> "Domingo";
    };
  }

  private CalendarioDiaDTO toDto(Calendario calendario) {

    ZoneId bogota = ZoneId.of("America/Bogota");

    ZonedDateTime inicioDiaBogota =
        calendario.getFecha().atStartOfDay(bogota);

    OffsetDateTime fechaUtc =
        inicioDiaBogota
            .withZoneSameInstant(ZoneOffset.UTC)
            .toOffsetDateTime();

    CalendarioDiaDTO dto = new CalendarioDiaDTO();

    dto.setId(calendario.getId());
    dto.setFecha(fechaUtc);
    dto.setTipo(calendario.getTipo());
    dto.setDescripcion(calendario.getDescripcion());

    return dto;
  }
}
