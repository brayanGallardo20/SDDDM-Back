package pe.gob.minjus.sisarb.model.domain;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Persona extends GenericDomain{
	
	private Integer idPersona;
	private String nombre;
	private String apellidoP;
	private String apellidoM;
	private Date fechaNac;
	private int celular;
	
}