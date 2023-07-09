package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametroBusquedaRequest extends GenericBusquedaRequest{

    private String concepto;

}
