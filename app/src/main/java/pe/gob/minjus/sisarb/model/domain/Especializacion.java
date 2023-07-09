package pe.gob.minjus.sisarb.model.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Especializacion extends GenericDomain{

    private Integer especializacionId;

    @NotEmpty(message = "El nombre es obligatorio")
    @Size( max = 50,message = "La longitud máxima del nombre es de 50 caracteres")
    private String nombre;
}
