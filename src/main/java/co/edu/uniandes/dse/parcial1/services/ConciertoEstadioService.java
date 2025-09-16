package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.ConciertoRepository;
import co.edu.uniandes.dse.parcial1.repositories.EstadioRepository;
import jakarta.transaction.Transactional;
@Slf4j
@Service
public class ConciertoEstadioService {

    @Autowired
    ConciertoRepository conciertoRepository;

    @Autowired
    EstadioRepository estadioRepository;

    @Transactional
    public EstadioEntity addConcierto(Long conciertoId, Long estadioId) throws IllegalOperationException{
        log.info("Inicia proceso de asociarle un concierto al estadio con id = {0}", estadioId);

        Optional<ConciertoEntity> conciertoEntity = conciertoRepository.findById(conciertoId);
        Optional<EstadioEntity> estadioEntity= estadioRepository.findById(estadioId);

        ConciertoEntity concierto = conciertoEntity.get();
        EstadioEntity estadio = estadioEntity.get();

        if (concierto.getCapacidadDeAforo()>estadio.getAforoMaximoEstadio()){
            throw new IllegalOperationException("La capacidad de un concierto no debe superar la capacidad del estadio.");
        }

        if (concierto.getPresupuesto()>estadio.getPrecioAlquiler()){
            throw new IllegalOperationException("El precio de alquiler del estadio no debe superar el presupuesto del concierto.");

        }

        LocalDateTime nuevaFecha = concierto.getFechaDelConcierto();

        if (estadio.getConciertos()!= null){
            for (ConciertoEntity existente : estadio.getConciertos()){
                if (existente.getFechaDelConcierto() == null) continue;
                Duration diferencia = Duration.between(existente.getFechaDelConcierto(), nuevaFecha).abs();
                if (diferencia.compareTo(Duration.ofDays(2)) < 0) {
                throw new IllegalOperationException(
                    "Debe existir un tiempo mínimo de 2 días entre conciertos del mismo estadio"
                );
            }
        }
        }

        concierto.setEstadio(estadio);
        estadio.getConciertos().add(concierto);

        return estadio;

        

        

    }

}
