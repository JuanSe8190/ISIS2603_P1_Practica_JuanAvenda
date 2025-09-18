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
import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;



@DataJpaTest
@Transactional
@Import({ EstadioService.class, ConciertoEstadioService.class })
public class ConciertoEstadioService {

    @Autowired
    private ConciertoEstadioService conciertoEstadioService;

    @Autowired
    private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

    private List<ConciertoEntity> conciertoList = new ArrayList<>();
    private List<EstadioEntity> estadioList=new ArrayList<>();

    private ConciertoEntity conciertoExistente;

    @BeforeEach
	void setUp() {
		clearData();
		insertData();
    }

    private void clearData(){
        entityManager.getEntityManager().createQuery("delete from EstadioEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from ConciertoEntity").executeUpdate();

    }

    private void insertData(){
        for (int i = 0; i < 3; i++) {
            EstadioEntity estadios=factory.manufacturePojo(EstadioEntity.class);
            entityManager.persist(estadios);
            estadioList.add(estadios);
            if (i==0){
                conciertoList.get(i).setEstadio(estadios);
            }
        }
        for (int i = 0; i < 3; i++) {
            ConciertoEntity conciertos=factory.manufacturePojo(ConciertoEntity.class);
            entityManager.persist(conciertos);
            conciertoList.add(conciertos);
        }
    }

    @Test
    void testAddConciert() throws EntityNotFoundException, IllegalOperationException {
        EstadioEntity newEstadio= factory.manufacturePojo(EstadioEntity.class);
        newEstadio.setConciertos(conciertoList);
        entityManager.persist(newEstadio);

        ConciertoEntity concierto=factory.manufacturePojo(ConciertoEntity.class);
        entityManager.persist(concierto);

        


    }



}
