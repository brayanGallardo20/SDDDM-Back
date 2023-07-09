package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditoriaBusquedaRequest extends GenericBusquedaRequest{
    private String tabla;
    private String auditTipo;
    private String auditFechaInicio;
    private String auditFechaFin;
    private String usuario;


}
