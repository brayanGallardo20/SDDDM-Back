package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GenericBusquedaRequest {
    @NotNull(message = "La cantidad de registro por página no debe ser nulo")
    @Min(value = 5,message = "El valor mínimo para la cantidad de página es 5")
    private Integer cantidadPorPagina;

    @NotNull(message = "La fina inicio no debe ser nulo")
    @Min(value = 0,message =  "El valor mínimo para fila inicio es 0")
    private Integer filaInicio;
}
