package com.cibertec.pi.database.repository;

import com.cibertec.pi.database.entidad.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SedeRepository extends JpaRepository<Sede, Long> {

<<<<<<< HEAD
    // Método para encontrar sedes por estado
=======
>>>>>>> 69a81185781afd62b4fb062d3ef10be64ea38371
    List<Sede> findByEstado(Boolean estado);
}
