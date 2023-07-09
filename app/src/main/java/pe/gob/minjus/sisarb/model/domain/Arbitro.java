package pe.gob.minjus.sisarb.model.domain;

import lombok.*;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Arbitro   extends  GenericDomain{

    private Integer arbitroId;

    @NotNull(message = "La persona es obligatorio")
    private Integer personaId;

    @NotNull(message = "El tipo de arbitro es obligatorio")
    private Integer tipoArbitroId;

    private Integer entidadFinancieraId;

    @NotEmpty(message = "El número de cuenta es obligatorio")
    @Size( max = 15,message = "La longitud máxima para el numero de cuenta es 15 caracteres")
    private String nroCuenta;

    @NotEmpty(message = "El número de cuenta CCI es obligatorio")
    @Size( max = 25,message = "La longitud máxima para el numero de cuenta es 25 caracteres")
    private String nroCuentaCci;

    @NotEmpty(message = "El número de cuenta CCI es obligatorio")
    @Size( max = 150,message = "La longitud máxima para el numero de cuenta es 150 caracteres")
    private String paginaWeb;

    @Min(value = 0,message =  "El valor solo puede ser 0 o 1")
    @Max(value = 1,message =  "El valor solo puede ser 0 o 1")
    private Integer habilitado;

    @Min(value = 0,message =  "El valor solo puede ser 0 o 1")
    @Max(value = 1,message =  "El valor solo puede ser 0 o 1")
    private Integer fallecido;

    @Size( max = 150,message = "La longitud máxima para la profesión es 150 caracteres")
    private String profesion;

    @Size( max = 250,message = "La longitud máxima para la observación es 250 caracteres")
    private String observacion;

    @Size( max = 1,message = "La longitud máxima para el RUC es de 11 caracteres")
    private String ruc;

    private List<ArbitroEspecializacion> listadoArbitroEspecializacion = new ArrayList<>();

}
