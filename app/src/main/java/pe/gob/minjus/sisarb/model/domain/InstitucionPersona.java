package pe.gob.minjus.sisarb.model.domain;

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
public class InstitucionPersona extends GenericDomain {

	private Integer institucionPersonaId;
	 
    private Integer institucionId;
    
    private Integer personaId;
    
    private Integer personaCargoId;
    
    private Integer activo; 

    @JsonIgnore
    public String getNameTable(){
        return "TRS_INSTITUCION_PERSONA";
    }
}

