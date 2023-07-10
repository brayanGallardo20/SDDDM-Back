package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaBusquedaRequest extends GenericBusquedaRequest{
    private String nombre;
    private String apellidoP;
    private String apellidoM;
}
