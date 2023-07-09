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
public class InstitucionResponse extends GenericDomain {

    private Long institucionId;
    
    private Long tipoOperadorArbitralId;
    
    private String ubigeoId;
    
    private String ruc;

    private String razonSocial;
    
    private String direccion;
    
    private String telefono;
    
    private String correo;
    
    private String paginaWeb;
     
    private String horarioLvInicio;
    
    private String horarioLvFin;
    
    private String horarioSaInicio;
    
    private String horarioSaFin;
    
    private Integer activo;
    
}