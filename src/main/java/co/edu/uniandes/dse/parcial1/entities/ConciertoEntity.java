package co.edu.uniandes.dse.parcial1.entities;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;


import java.time.LocalDateTime;


import jakarta.persistence.*;

@Data
@Entity
public class ConciertoEntity extends BaseEntity {

    private String nombre;
    private Long presupuesto;
    private String nombreDelArtista;
    private Integer capacidadDeAforo;

    @PodamExclude
    @ManyToOne
    private EstadioEntity estadio;
    
    @Temporal(TemporalType.DATE)
    private LocalDateTime FechaDelConcierto;
}
