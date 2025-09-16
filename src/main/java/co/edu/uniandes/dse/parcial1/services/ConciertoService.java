package co.edu.uniandes.dse.parcial1.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.ConciertoRepository;
import jakarta.transaction.Transactional;

@Slf4j
@Service
public class ConciertoService {

    @Autowired 
    ConciertoRepository conciertoRepository;

    @Transactional
    public ConciertoEntity crearConcierto(ConciertoEntity concierto) throws IllegalOperationException {
        log.info("Inicia proceso de creación del concierto");
        LocalDateTime fechaActual = LocalDateTime.now();

        if (fechaActual.isAfter(concierto.getFechaDelConcierto())){
            throw new IllegalOperationException("la fecha de un concierto no puede ser en el pasado");
        }
        if (concierto.getCapacidadDeAforo()<10){
            throw new IllegalOperationException("La capacidad de un concierto debe ser mayor a 10 personas");
        }
        if (concierto.getPresupuesto()<1000){
            throw new IllegalOperationException("el presupuesto del concierto debe ser superior a 1000 dolares");

        }
        log.info("termina el proceso de creacion del concierto");
        return conciertoRepository.save(concierto);


        
    }

}
