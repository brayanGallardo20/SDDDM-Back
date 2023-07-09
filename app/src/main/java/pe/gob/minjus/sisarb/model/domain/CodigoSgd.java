package pe.gob.minjus.sisarb.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CodigoSgd  extends GenericDomain {
    private Integer codigoSgdId;
    private Integer expedienteId;

    @NotEmpty(message = "El asunto es obligatorio")
    @Size( max = 30,message = "La longitud máxima del asunto es de 30 caracteres")
    private String asunto;

    @NotNull(message = "La fecha emisión es obligatorio")
    private LocalDate fechaEmision;

    @NotEmpty(message = "El celular es obligatorio")
    @Size( max = 10,message = "La longitud máxima del celular es de 10 caracteres")
    private String celular;

    @NotEmpty(message = "El remitente es obligatorio")
    @Size( max = 25,message = "La longitud máxima del remitente es de 25 caracteres")
    private String remitente;

    @NotEmpty(message = "El correo es obligatorio")
    @Size( max = 35,message = "La longitud máxima del correo es de 35 caracteres")
    private String correo;

    private String fechaEmisionFormat;

    @JsonIgnore
    public String getNameTable(){
        return "MAE_CODIGO_SGD";
    }
}
