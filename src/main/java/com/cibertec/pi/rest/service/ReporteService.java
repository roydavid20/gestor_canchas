package com.cibertec.pi.rest.service;

import com.cibertec.pi.database.dto.ReponseSemanaDto;
import com.cibertec.pi.database.repository.ReservaRepository;
import com.cibertec.pi.rest.response.ReservaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final ReservaRepository reservaRepository;

    public List<ReponseSemanaDto> reporteSemanalCanchaId(Long canchaId){
        return reservaRepository.reporteSemanalCanchaId(canchaId);
    }

    public List<ReservaResponse> reporteDiaPorCanchaId(String date, Long canchaId){
        List<ReservaResponse> response = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaReserva = LocalDate.parse(date, formatter);
        reservaRepository.getAllByCanchaAndFecha(canchaId, fechaReserva).forEach(bean -> {
            response.add(new ReservaResponse(bean));
        });
        return response;
    }

    public List<ReservaResponse> reportePorFecha(String fIni, String fFin){
        List<ReservaResponse> response = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaIni = LocalDate.parse(fIni, formatter);
        LocalDate fechaFin = LocalDate.parse(fFin, formatter);
        reservaRepository.getAllBFecha(fechaIni, fechaFin).forEach(bean -> {
            response.add(new ReservaResponse(bean));
        });
        return response;
    }
}
