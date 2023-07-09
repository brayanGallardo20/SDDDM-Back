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
public class Institucion extends GenericDomain{

    private Integer institucionId;
    
    private Integer tipoOperadorArbitralId;
    
    @NotNull(message = "El ubigeo es obligatorio")
    private String ubigeoId; 
    
    @NotEmpty(message = "El ruc es obligatorio")
    @Size( max = 11,message = "La longitud máxima del ruc es de 11 caracteres")
    private String ruc;
    
    @NotNull(message = "El razon social obligatorio")
    @Size( max = 100,message =  "La longitud máxima de la Razon Social es de 100 caracteres")
    private String razonSocial;
    
    @NotNull(message = "La dirección es obligatorio")
    @Size(max = 250,message =  "La longitud máxima de la dirección es de 250 caracteres")
    private String direccion;   
    
    @NotNull(message = "La telefono es obligatorio")
    @Size(max = 12,message =  "La longitud máxima del telefono es de 12 caracteres")
    private String telefono;
    
    private String correo;

    @NotNull(message = "La página web es obligatorio")
    @Size(max = 240,message =  "La longitud máxima de la página web es de 240 caracteres")
    private String paginaWeb;

    @NotNull(message = "El horario lunes a viernes inicio es obligatorio")
    @Size(max = 240,message =  "La longitud máxima del horario lunes a viernes inicio es de 5 caracteres")
    private String horarioLvInicio;
    
    @NotNull(message = "El horario lunes a viernes fin es obligatorio")
    @Size(max = 240,message =  "La longitud máxima del horario lunes a viernes fin es de 5 caracteres")
    private String horarioLvFin;
    
    @NotNull(message = "El horario de sábado Inicio es obligatorio")
    @Size(max = 240,message =  "La longitud máxima del  horario sábado Inicio es de 5 caracteres")
    private String horarioSaInicio;
    
    @NotNull(message = "El horario sábado Fin es obligatorio")
    @Size(max = 240,message =  "La longitud máxima del  horario sábado Fin es de 5 caracteres")
    private String horarioSaFin;
    
    private Integer activo;
    
    @JsonIgnore
    public String getNameTable(){
        return "TRS_INSTITUCION";
    }
    
}