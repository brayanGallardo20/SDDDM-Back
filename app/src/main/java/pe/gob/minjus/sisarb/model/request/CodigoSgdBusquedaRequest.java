package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodigoSgdBusquedaRequest extends GenericBusquedaRequest {
    private Integer expedienteId;
    private String asunto;
    private String fechaEmision;
    private String celular;
    private String remitente;
    private String correo;
    private String auditUsuarioCreacion;
    private String auditFechaCreacion;
    private String auditUsuarioModifica;
    private String auditFechaModifica;
}
