package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaestraBusquedaRequest extends GenericBusquedaRequest {

	private String tablaMaestra;
    private String nombre;


}