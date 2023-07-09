package pe.gob.minjus.sisarb.model.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sede extends  GenericDomain{
    private Integer sedeId;
    @NotNull(message = "La institución es obligatorio")
    @Min(value = 0,message =  "El identificador de la institución debe ser mayor a 0")
    private Integer institucionId;
    @NotEmpty(message = "El nombre de la sede es obligatorio")
    @Size( max = 150,message = "La longitud máxima para el nombre es de 150 caracteres")
    private String nombre;
    @NotEmpty(message = "La dirección es obligatorio")
    @Size( max = 250,message = "La longitud máxima para la dirección es de 250 caracteres")
    private String direccion;
    @NotEmpty(message = "La resolución es obligatorio")
    @Size( max = 150,message = "La longitud máxima para el número de resolución es de 150 caracteres")
    private String numeroResolucionPartida;
    @NotEmpty(message = "La teléfono es obligatorio")
    @Size( max = 12,message = "La longitud máxima para el teléfono es 12 caracteres")
    private String telefono;
    @NotEmpty(message = "El correo es obligatorio")
    @Size( max = 100,message = "La longitud máxima para el correo es de 100 caracteres")
    @Email(message = "Ingrese un email válido")
    private String correo;

    @NotEmpty(message = "El ubigeo es obligatorio")
    @Size( max = 6,message = "La longitud máxima del ubigeo es de 6 caracteres")
    private String ubigeoId;

    private  List<SedeEspecialidad> listadoSedeEspecialidad = new ArrayList<>();
    private  List<SedeMateriaArbitral> listadoSedeMateriaArbitral = new ArrayList<>();


}
