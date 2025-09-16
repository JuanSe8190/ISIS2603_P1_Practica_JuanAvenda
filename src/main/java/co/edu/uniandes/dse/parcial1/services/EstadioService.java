package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.EstadioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import co.edu.uniandes.dse.parcial1.repositories.ConciertoRepository;
@Slf4j
@Service
public class EstadioService {

    @Autowired
    EstadioRepository estadioRepository;

    @Autowired
    ConciertoRepository conciertoRepository;

    @Transactional
    public EstadioEntity crearEstadio (EstadioEntity estadio) throws IllegalOperationException{
        log.info("inicia proceso de creacion del estadio");
        if (estadio.getNombreCiudad().length()<3){
            throw new IllegalOperationException("El nombre de la ciudad debe ser una cadena de caracteres de mínimo 3 letras");

        }
        if (estadio.getAforoMaximoEstadio()<1000000 && estadio.getAforoMaximoEstadio()>1000 ){
            throw new IllegalOperationException("La capacidad del estadio debe ser superior a 1000 personas y menor a 1000000");

        }
        if(estadio.getPrecioAlquiler()<100000){
            throw new IllegalOperationException("El precio de alquiler debe ser superior a 100000 dólares");

        }
        log.info("termina el proceso de creacion del estadio");
        return estadioRepository.save(estadio);
    }

}
