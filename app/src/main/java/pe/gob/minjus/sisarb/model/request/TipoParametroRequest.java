package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoParametroRequest {
 
    private Long tipoParametroId;
    private String nombre;
    private String descripcion;
    private String activo;
    private String auditUsuarioCreacion;
    private String auditUsuarioModifica;
    
}
