package pe.gob.minjus.sisarb.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Maestra extends GenericDomain {
	
	private Integer maestraId;
	private String tablaMaestra;
    private String nombre;
    
    @JsonIgnore
    public String getNameTable(){
        return "MAESTRA";
    }
	
    
}
