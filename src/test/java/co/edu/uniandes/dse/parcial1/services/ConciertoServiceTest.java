package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@DataJpaTest
@Transactional
@Import(ConciertoService.class)
public class ConciertoServiceTest {
    
    @Autowired
    private ConciertoService conciertoService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<ConciertoEntity> conciertoList= new ArrayList<>();

    @BeforeEach
    void setUp() {
                clearData();
                insertData();
        }

    private void clearData() {
		entityManager.getEntityManager().createQuery("delete from ConciertoEntity");
	}

    private void insertData(){


        for (int i=0; i<3; i++) {
            ConciertoEntity conciertoEntity=factory.manufacturePojo(ConciertoEntity.class);
            entityManager.persist(conciertoEntity);
            conciertoList.add(conciertoEntity);

        }
    }

    @Test
    void testCrearConcierto() throws IllegalOperationException, EntityNotFoundException {
        ConciertoEntity nuevoConcierto=factory.manufacturePojo(ConciertoEntity.class);

        ConciertoEntity resultado = conciertoService.crearConcierto(nuevoConcierto);
        assertNotNull(resultado);
        ConciertoEntity entity = entityManager.find(ConciertoEntity.class, resultado.getId());
        assertEquals(nuevoConcierto.getId(), entity.getId());
        assertEquals(nuevoConcierto.getNombre(), entity.getNombre());
        assertEquals(nuevoConcierto.getPresupuesto(), entity.getPresupuesto());
        assertEquals(nuevoConcierto.getNombreDelArtista(), entity.getNombreDelArtista());
        assertEquals(nuevoConcierto.getCapacidadDeAforo(), entity.getCapacidadDeAforo());
        assertEquals(nuevoConcierto.getId(), entity.getId());
        assertEquals(nuevoConcierto.getEstadio(), entity.getEstadio());
        assertEquals(nuevoConcierto.getFechaDelConcierto(), entity.getFechaDelConcierto());


    }
    void testCrearConicertoSinAforo() {
        assertThrows(IllegalOperationException.class, ()->{
            ConciertoEntity nuevoConcierto = factory.manufacturePojo(ConciertoEntity.class);
            nuevoConcierto.setCapacidadDeAforo(9);
            conciertoService.crearConcierto(nuevoConcierto);
        });

    }

    


}
