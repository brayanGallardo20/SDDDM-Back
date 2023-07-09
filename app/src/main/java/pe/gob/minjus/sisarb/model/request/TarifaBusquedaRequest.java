package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TarifaBusquedaRequest extends GenericBusquedaRequest {

    private Long institucionId;
    
    private String cuantiaDesde;
    
}
