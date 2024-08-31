package com.cibertec.pi.rest.response;

import com.cibertec.pi.database.entidad.Reserva;
import com.fasterxml.jackson.annotation.JsonProperty;
<<<<<<< HEAD
=======
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
>>>>>>> 69a81185781afd62b4fb062d3ef10be64ea38371
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Data
<<<<<<< HEAD
@NoArgsConstructor
public class ReservaResponse {

=======
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaResponse {
    @Id
>>>>>>> 69a81185781afd62b4fb062d3ef10be64ea38371
    @JsonProperty("id")
    private Long id;

    @JsonProperty("fecha_creacion")
    private String fechaCreacion;

    @JsonProperty("fecha_reserva")
    private String fechaReserva;

    @JsonProperty("hora_reserva")
    private Integer horaReserva;

    @JsonProperty("cancha")
    private String cancha;

    @JsonProperty("observacion")
    private String observacion;

    @JsonProperty("importe")
    private Double importe;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("cliente")
    private String cliente;

<<<<<<< HEAD
=======
    @JsonProperty("pdf_url")
    private String pdfUrl;

    @JsonProperty("sede")
    private String sede;

>>>>>>> 69a81185781afd62b4fb062d3ef10be64ea38371
    // Constructor que acepta un objeto Reserva
    public ReservaResponse(Reserva reserva) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.id = reserva.getId();
        this.fechaCreacion = dateFormat.format(reserva.getFechaCreacion());
        this.fechaReserva = reserva.getFechaReserva().format(formatter);
        this.horaReserva = reserva.getHoraReserva();  // Asumiendo que es un Integer representando la hora en 24h
        this.cancha = reserva.getCancha().getNumero() + " | " + reserva.getCancha().getTipoCancha().name();
        this.observacion = reserva.getObservacion();
        this.importe = reserva.getImporte();
        this.estado = reserva.getEstado().name();
        this.cliente = reserva.getCliente().getPersona().getNombreCompleto();
        this.sede = reserva.getCancha().getSede().getNombre();
    }

    // Constructor adicional que acepta parámetros individuales
    public ReservaResponse(Long id, String fechaCreacion, String fechaReserva, Integer horaReserva,
                           String cancha, String observacion, Double importe, String estado, String cliente) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
        this.cancha = cancha;
        this.observacion = observacion;
        this.importe = importe;
        this.estado = estado;
        this.cliente = cliente;
    }
}
