package pe.gob.minjus.sisarb.model.domain;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TipoParametro extends GenericDomain {
    private Integer tipoParametroId;
    private String nombre;
    private String descripcion;

    @JsonIgnore
    public String getNameTable(){
        return "MAE_TIPO_PARAMETRO";
    }
    
}
