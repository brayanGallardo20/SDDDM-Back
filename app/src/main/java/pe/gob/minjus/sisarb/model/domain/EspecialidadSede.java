package pe.gob.minjus.sisarb.model.domain;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EspecialidadSede extends GenericDomain{

    private Long especialidadSedeId;
    private Long sede;
    private String nombre;
    private Integer activo;
    
    @JsonIgnore
    public String getNameTable(){
        return "TRS_ESPECIALIDAD_SEDE";
    }
}
