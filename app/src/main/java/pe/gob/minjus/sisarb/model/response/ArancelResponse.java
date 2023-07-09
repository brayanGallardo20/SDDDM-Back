package pe.gob.minjus.sisarb.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pe.gob.minjus.sisarb.model.domain.GenericDomain;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArancelResponse extends GenericDomain {

    private Long arancelId;
       
    private Long institucionId;
    
    private String servicio;

    private String requisito;
    
    private String plazoAtencion;
    
    private String derechoPagoUit;
    
    private Integer activo;
    
    
}