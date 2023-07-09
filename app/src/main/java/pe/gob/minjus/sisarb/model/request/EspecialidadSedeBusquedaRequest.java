package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EspecialidadSedeBusquedaRequest extends GenericBusquedaRequest{

    private String nombre;
    private Long sedeId;
    
}
