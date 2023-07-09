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
public class Tarifa extends GenericDomain {

    private Integer tarifaId;
    
    private Integer institucionId;
    
    @NotNull(message = "La cuantía desde es obligatorio")
    private String cuantiaDesde;
    
    @NotNull(message = "La cuantía hasta es obligatorio")
    private String cuantiaHasta;

    @NotNull(message = "El honorario arbitro es obligatorio")
    @Size( max = 100,message =  "La longitud máxima del nombre es de 100 caracteres")
    private String honorarioArbitro;
    
    @NotEmpty(message = "El honorario a pagar c/ parte es obligatorio")
    @Size( max = 11,message = "La longitud máxima del ruc es de 11 caracteres")
    private String honorarioPagarParte;
    
    @NotNull(message = "La cantidad  es obligatorio")
    private String cantidad;
    
    @JsonIgnore
    public String getNameTable(){
        return "TRS_TARIFA";
    }
    
}