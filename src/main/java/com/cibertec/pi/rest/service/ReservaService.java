package com.cibertec.pi.rest.service;

import com.cibertec.pi.constant.EstadoReservaEnum;
import com.cibertec.pi.database.entidad.Cancha;
import com.cibertec.pi.database.entidad.Cliente;
import com.cibertec.pi.database.entidad.Reserva;
import com.cibertec.pi.database.entidad.Sede;
import com.cibertec.pi.database.repository.CanchaRepository;
import com.cibertec.pi.database.repository.ClienteRepository;
import com.cibertec.pi.database.repository.ReservaRepository;
import com.cibertec.pi.database.repository.SedeRepository;
import com.cibertec.pi.rest.request.CrearReservaRequest;
import com.cibertec.pi.rest.response.CanchaResponse;
import com.cibertec.pi.rest.response.ReservaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final CanchaRepository canchaRepository;
    private final ClienteRepository clienteRepository;
    private final SedeRepository sedeRepository;

    public List<CanchaResponse> listarCanchasDisponibles(Long sedeId) {
        List<Cancha> canchaList = canchaRepository.findByEstadoIsTrueAndSedeId(sedeId);

        List<CanchaResponse> responses = new ArrayList<>();

        canchaList.forEach(bean -> {
            responses.add(new CanchaResponse(bean));
        });
        return responses;
    }

    public List<Integer> verHorasDisponibles(Long canchaId, String fecha) {
        Cancha cancha = canchaRepository.findById(canchaId)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la cancha con ID: " + canchaId));
        List<Integer> horasDisponibles = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha, formatter);

        for (int i = cancha.getDisHrInicio(); i < cancha.getDisHrFin(); i++) {
            horasDisponibles.add(i);
        }

        List<Integer> horasOcupadas = reservaRepository.verHorasOcupadas(canchaId, date);
        horasDisponibles.removeAll(horasOcupadas);

        return horasDisponibles;
    }


    public ReservaResponse crearReserva(CrearReservaRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Obtener la cancha, cliente y sede desde la base de datos
        Cancha cancha = canchaRepository.findById(request.getCanchaId()).orElseThrow(() -> new RuntimeException("Cancha no encontrada"));
        Cliente cliente = clienteRepository.findById(request.getClienteId()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Sede sede = sedeRepository.findById(request.getSedeId()).orElseThrow(() -> new RuntimeException("Sede no encontrada"));

        // Convertir el Integer de horaReserva a LocalTime
        Integer horaReserva = request.getHoraReserva();
        LocalTime horaReservaTime = LocalTime.of(horaReserva, 0); // Asumiendo que "horaReserva" es solo la hora

        // Crear la entidad Reserva con los datos proporcionados
        Reserva reserva = Reserva.builder()
                .fechaCreacion(new Timestamp(new Date().getTime()))  // Timestamp de la creación
                .fechaReserva(LocalDate.parse(request.getFechaReserva(), formatter))  // Parsear la fecha de la reserva
                .horaReserva(horaReservaTime)  // Usar LocalTime para la hora de la reserva
                .cancha(cancha)  // Asignar la cancha
                .sede(sede)  // Asignar la sede
                .observacion(request.getObservacion())  // Agregar la observación
                .importe(request.getImporte())  // Asignar el importe
                .estado(EstadoReservaEnum.PENDIENTE)  // Estado inicial de la reserva
                .cliente(cliente)  // Asignar el cliente
                .direccion(request.getDireccion())  // Asignar la dirección
                .build();

        // Guardar la reserva en la base de datos
        reserva = reservaRepository.save(reserva);

        // Retornar la respuesta de la reserva creada
        return new ReservaResponse(reserva);
    }



    public ReservaResponse obtenerReserva(Long reservaId) {
        Reserva reserva = reservaRepository.findById(reservaId).get();
        return new ReservaResponse(reserva);
    }
}
