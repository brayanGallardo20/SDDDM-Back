package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitucionBusquedaRequest extends GenericBusquedaRequest{

    private String razonSocial;
    private String ruc;
    private Long tipoOperadorArbitralId;
}
