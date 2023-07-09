package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaestraRequest {
 
	private String tablaMaestra;
    private String nombre;
    private String estado;
    private String auditUsuarioCreacion;
    private String auditUsuarioModifica;
    
}
