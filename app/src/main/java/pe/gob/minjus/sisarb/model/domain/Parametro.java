package pe.gob.minjus.sisarb.model.domain;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Parametro extends GenericDomain {
    private Integer parametroId;
    private Integer codigo;
    private String concepto;
    private String detalle;
    private String valor;

    @JsonIgnore
    public String getNameTable(){
        return "MAE_PARAMETRO";
    }
    
}
