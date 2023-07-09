package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectivoBusquedaRequest extends GenericBusquedaRequest{
    private int tipoDocumentoId;
    private String numeroDocumento;
    private int institucionId;
}
