package pe.gob.minjus.sisarb.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonaNatural extends GenericDomain{
    private Integer personaNaturalId;

    @NotNull(message = "El tipo documento es obligatorio")
    private Integer tipoDocumentoId;

    @NotEmpty(message = "El nombre es obligatorio")
    @Size( max = 40,message = "La longitud máxima para los nombres es 40 caracteres")
    private String nombre;

    private String apellidoPaterno;
    private String apellidoMaterno;
    private String numeroDocumento;
    private Integer activo;
    private Integer ubigeoId;
    private String correo;
    private String telefono;
    private String direccion;

    private SedePersona sedePersona;

    public PersonaNatural(Integer personaNaturalId) {
        this.personaNaturalId = personaNaturalId;
    }
    @JsonIgnore
    public String getNameTable(){
        return "TRS_PERSONA";
    }




}


