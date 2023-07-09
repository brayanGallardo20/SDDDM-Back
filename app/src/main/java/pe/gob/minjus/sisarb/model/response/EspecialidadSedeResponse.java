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
public class EspecialidadSedeResponse extends GenericDomain {

    private Long especialidadSedeId;
    
    private Long sedeId;

    private String nombre;

    private Integer activo;
    
}