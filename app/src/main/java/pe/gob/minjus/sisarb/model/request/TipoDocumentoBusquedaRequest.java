package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoDocumentoBusquedaRequest extends GenericBusquedaRequest{
    private String nombre;
    private String pide;
    private String auditUsuarioCreacion;
    private String auditFechaCreacion;
    private String auditUsuarioModifica;
    private String auditFechaModifica;
}
