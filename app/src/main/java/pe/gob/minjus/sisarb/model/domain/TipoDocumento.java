package pe.gob.minjus.sisarb.model.domain;

import lombok.*;
import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TipoDocumento extends GenericDomain {
    private Integer tipoDocumentoId;

    @NotEmpty(message = "El nombre es obligatorio")
    @Size( max = 50,message = "La longitud máxima del nombre es de 50 caracteres")
    private String nombre;

    @NotNull(message = "El indicador PIDE es obligatorio")
    @Min(value = 0,message =  "Solo se admiten los valores 0 y 1")
    @Max(value = 1,message =  "Solo se admiten los valores 0 y 1")
    private Integer pide;
    
    private String mensaje;
    
    private String formato;
    
    private Integer numeroCaracteres;
    
    private Integer activo;


    
}
