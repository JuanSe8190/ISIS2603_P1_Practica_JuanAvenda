package co.edu.uniandes.dse.parcial1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class EstadioEntity extends BaseEntity {

    private String nombre;
    private Long precioAlquiler;
    private String nombreCiudad;
    private Integer aforoMaximoEstadio;

    @PodamExclude
    @OneToMany(mappedBy =  "concierto")
    private List<ConciertoEntity> conciertos= new ArrayList<ConciertoEntity>();
    


}
