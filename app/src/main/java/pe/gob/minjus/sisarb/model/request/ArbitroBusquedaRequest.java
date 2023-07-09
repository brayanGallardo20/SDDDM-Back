package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArbitroBusquedaRequest extends GenericBusquedaRequest{
    private Integer especialidadId;
    private Integer tipoDocumentoId;
    private Integer declaracionInteresId;
    private Integer sancionId;
    private String nombreCompleto;
    private String auditUsuarioCreacion;
    private String auditFechaCreacion;
    private String auditUsuarioModifica;
    private String auditFechaModifica;
}