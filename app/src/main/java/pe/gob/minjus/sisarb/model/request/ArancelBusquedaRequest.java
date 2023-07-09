package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArancelBusquedaRequest extends GenericBusquedaRequest{

    private Long institucionId;
    
    private String servicio;
}
