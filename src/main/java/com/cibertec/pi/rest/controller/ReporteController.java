package com.cibertec.pi.rest.controller;

import com.cibertec.pi.database.dto.ReponseSemanaDto;
import com.cibertec.pi.rest.response.ReservaResponse;
import com.cibertec.pi.rest.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reporte")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping("/semana/{cancha_id}")
    public List<ReponseSemanaDto> reporteSemanalCanchaId(@PathVariable("cancha_id") Long canchaId) {
        return reporteService.reporteSemanalCanchaId(canchaId);
    }

    @GetMapping("/dia/{cancha_id}/{fecha}")
    public List<ReservaResponse> reporteDiaPorCanchaId(@PathVariable("cancha_id") Long canchaId, @PathVariable("fecha") String fecha) {
        return reporteService.reporteDiaPorCanchaId(fecha, canchaId);
    }

    @GetMapping("/fecha/{fecha_ini}/{fecha_fin}")
    public List<ReservaResponse> reportePorFecha(@PathVariable("fecha_ini") String fechaIni, @PathVariable("fecha_fin") String fechaFin) {
        return reporteService.reportePorFecha(fechaIni, fechaFin);
    }
}
