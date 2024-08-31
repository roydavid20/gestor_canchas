package com.cibertec.pi.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearReservaRequest {

    @JsonProperty("fecha_reserva")
    private String fechaReserva;

    @JsonProperty("hora_reserva")
    private Integer horaReserva;

    @JsonProperty("cancha_id")
    private Long canchaId;

    @JsonProperty("sede_id")
    private Long sedeId;

    @JsonProperty("cliente_id")  // Añadir el campo cliente_id
    private Long clienteId;  // Este es el nuevo campo para clienteId

    @JsonProperty("observacion")
    private String observacion;

    @JsonProperty("importe")
    private Double importe;

    @JsonProperty("direccion")
    private String direccion;
}
