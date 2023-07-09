package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametroConfiguracionBusquedaRequest extends  GenericBusquedaRequest {
    private Integer parametroConfiguracionId;
    private String ambienteConfiguracion;
    private String nombre;
    private String detalle;
    private String valor;
    private String fechaCreacion;
    private String grupo;
    private Integer estado;
    
}
