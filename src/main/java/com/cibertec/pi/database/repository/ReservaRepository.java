package com.cibertec.pi.database.repository;

import com.cibertec.pi.database.dto.ReponseSemanaDto;
import com.cibertec.pi.database.entidad.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query(value = "select r.horaReserva from Reserva r where r.cancha.id = :canchaId and r.fechaReserva = :fechaReserva and r.estado != 'COMPLETA'")
    List<Integer> verHorasOcupadas(@Param("canchaId") Long canchaId, @Param("fechaReserva") LocalDate fechaReserva);

    @Query(value = "SELECT r.id as reservaId, dayname(fecha_reserva) as diaSemana, hora_reserva as horaInicio , concat(p.nombres, ' ', p.apellidos) as cliente " +
            "FROM reserva r " +
            "inner join cliente c on r.cliente_id = c.id " +
            "inner join persona p on c.persona_id = p.nro_documento " +
            "WHERE WEEK(r.fecha_reserva, 1) = WEEK(CURDATE(), 1) " +
            "AND YEAR(r.fecha_reserva) = YEAR(CURDATE()) and cancha_id = :canchaId order by r.fecha_reserva asc", nativeQuery = true)
    List<ReponseSemanaDto> reporteSemanalCanchaId(@Param("canchaId") Long canchaId);

    @Query(value = "select r from Reserva r where r.cancha.id = :canchaId and r.fechaReserva = :fechaReserva")
    List<Reserva> getAllByCanchaAndFecha(@Param("canchaId") Long canchaId, @Param("fechaReserva") LocalDate fechaReserva);

    @Query(value = "select r from Reserva r where r.fechaReserva >= :fechaIni and r.fechaReserva <= :fechaFin")
    List<Reserva> getAllBFecha(@Param("fechaIni") LocalDate fechaIni, @Param("fechaFin") LocalDate fechaFin);
}
