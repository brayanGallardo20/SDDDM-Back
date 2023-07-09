package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SedeBusquedaRequest extends  GenericBusquedaRequest{
    @NotNull(message = "La institución es obligatoria")
    @Min(value = 1,message = "El valor mínimo del identificador de la institución es 1")
    private Integer institucionId;

    private String nombre;
}
