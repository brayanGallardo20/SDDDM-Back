package pe.gob.minjus.sisarb.model.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Arancel extends GenericDomain {

    private Integer institucionId;
    
    private Integer arancelId;
    
    @NotNull(message = "El servicio es obligatorio")
    @Size(max = 100,message =  "La longitud máxima del servicio es de 240 caracteres")
    private String servicio;
    
    @NotNull(message = "El requisito es obligatorio")
    @Size(max = 500,message =  "La longitud máxima del requisito es de 240 caracteres")
    private String requisito;

    @NotNull(message = "El plazo de atención es obligatorio")
    private String plazoAtencion;
    
    @NotEmpty(message = "El derecho de pago % UIT es obligatorio")
    private String derechoPagoUit;
    
    @JsonIgnore
    public String getNameTable(){
        return "TRS_ARANCEL";
    }
    
}